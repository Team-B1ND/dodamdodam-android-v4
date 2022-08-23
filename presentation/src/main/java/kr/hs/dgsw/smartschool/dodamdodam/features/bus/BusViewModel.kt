package kr.hs.dgsw.smartschool.dodamdodam.features.bus

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
    private val _applyBusState = MutableStateFlow(ApplyBusState(isLoading = false))
    private val _changeBusState = MutableStateFlow(ChangeBusState(isLoading = false))

    val getBusListState: StateFlow<GetBusListState> = _getBusListState
    val getMyBusState: StateFlow<GetMyBusState> = _getMyBusState
    val applyBusState: StateFlow<ApplyBusState> = _applyBusState
    val changeBusState: StateFlow<ChangeBusState> = _changeBusState

    val busId = MutableLiveData<Int>(0)
    val hasBus = MutableLiveData<Boolean>(false)

    init {
        getBusList()
    }

    private fun getBusList(){
        busUseCases.getBus(Unit).divideResult(
            isLoading,
            { _getBusListState.value = GetBusListState(busList = it ?: emptyList<BusByDate>()) },
            { _getBusListState.value = GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }
    private fun getMyBus(){
        busUseCases.getMyBus(Unit).divideResult(
            isLoading,
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
                busUseCases.addBusApply(idx).divideResult(

                )
            }
            else -> {
                busId.value = idx
                busUseCases.updateBusApply(UpdateBusApplyRequest(originBusIdx = checkBus(), busIdx = idx))
            }
        }
    }
    fun cancelBus(idx:Int){
        busUseCases.deleteBusApply(idx)
    }
}
