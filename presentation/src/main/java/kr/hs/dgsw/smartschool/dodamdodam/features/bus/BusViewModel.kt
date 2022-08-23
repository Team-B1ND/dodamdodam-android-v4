package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    private val _getBusListState = MutableStateFlow(GetBusListState(isLoading = false))
    private val _getMyBusState = MutableStateFlow(GetMyBusState(isLoading = false))
    private val _addBusApplyState = MutableStateFlow(AddBusApplyState(isLoading = false))
    private val _updateBusApplyState = MutableStateFlow(UpdateBusApplyState(isLoading = false))
    private val _deleteBusApplyState = MutableStateFlow(DeleteBusApplyState(isLoading = false))

    val getBusListState: StateFlow<GetBusListState> = _getBusListState
    val getMyBusState: StateFlow<GetMyBusState> = _getMyBusState
    val addBusApplyState: StateFlow<AddBusApplyState> = _addBusApplyState
    val updateBusApplyState: StateFlow<UpdateBusApplyState> = _updateBusApplyState
    val deleteBusApplyState: StateFlow<DeleteBusApplyState> = _deleteBusApplyState

    val busId = MutableLiveData<Int>(0)
    val hasBus = MutableLiveData<Boolean>(false)

    private val isGetMyBusLoading = MutableLiveData<Boolean>()
    private val isGetBusLoading = MutableLiveData<Boolean>()
    private val isAddBusApplyLoading = MutableLiveData<Boolean>()
    private val isUpdateBusApplyLoading = MutableLiveData<Boolean>()
    private val isDeleteBusApplyLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isGetBusLoading, isGetMyBusLoading,isAddBusApplyLoading,isUpdateBusApplyLoading,isDeleteBusApplyLoading)
        getBusList()
    }

    private fun getBusList(){
        busUseCases.getBus(Unit).divideResult(
            isGetBusLoading,
            { _getBusListState.value = GetBusListState(busList = it ?: emptyList<BusByDate>()) },
            { _getBusListState.value = GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }
    private fun getMyBus(){
        busUseCases.getMyBus(Unit).divideResult(
            isGetMyBusLoading,
            {_getMyBusState.value = GetMyBusState(busList = it ?: emptyList<Bus>()) },
            {_getMyBusState.value = GetMyBusState(error = it?: "버스를 받아오지 못하였습니다.") }
        )
    }
    private fun checkBus():Int{
        if(_getMyBusState.value.busList.isEmpty()) return 0
        else return _getMyBusState.value.busList.get(0).idx
    }
    fun applyBus(idx:Int){
        when(checkBus()){
            0-> {
                Log.e("applyBus","정상적 실행")
                busUseCases.addBusApply(idx).divideResult(
                    isAddBusApplyLoading,
                    {_addBusApplyState.value = AddBusApplyState(success = "정상적으로 버스를 가져왔습니다.") },
                    {_addBusApplyState.value = AddBusApplyState(error = "정상적으로 버스를 가져오는데에 실패하였습니다.")}
                )
            }
            else -> {
                busId.value = idx
                Log.e("changeBus","정상적 실행")
                busUseCases.updateBusApply(UpdateBusApplyRequest(originBusIdx = checkBus(), busIdx = idx)).divideResult(
                        isUpdateBusApplyLoading,
                        {_updateBusApplyState.value = UpdateBusApplyState(success = "정상적으로 버스를 가져왔습니다.") },
                    {_updateBusApplyState.value = UpdateBusApplyState(error = "정상적으로 버스를 가져오는데에 실패하였습니다.")}
                )
            }
        }
    }
    fun cancelBus(idx:Int){
        Log.e("cancelBus","정상적 실행")
        busUseCases.deleteBusApply(idx).divideResult(
            isDeleteBusApplyLoading,
            {_deleteBusApplyState.value = DeleteBusApplyState(success = "정상적으로 버스를 삭제했습니다.") },
            {_deleteBusApplyState.value = DeleteBusApplyState(error = "정상적으로 버스를 삭제하는데에 실패하였습니다.")}
        )
    }
}
