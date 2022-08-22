package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    private val _busState = MutableStateFlow(BusState(isLoading = false))
    val busState: StateFlow<BusState> = _busState
    val busId = MutableLiveData<Int>(0)
    val hasBus = MutableLiveData<Boolean>(false)

    init {
        getBusList()
    }

    private fun getBusList(){
        busUseCases.getBus(Unit).divideResult(
            isLoading,
            { _busState.value = BusState(busByDateList = it ?: emptyList<BusByDate>()) },
            { _busState.value = BusState(error = it ?: "버스를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }
    private fun getMyBus(){
        busUseCases.getMyBus(Unit).divideResult(
            isLoading,
            {_busState.value = BusState(busList = it ?: emptyList<Bus>())},
            {_busState.value = BusState(error = it?: "버스를 받아오지 못하였습니다.")}
        )
    }
    private fun checkBus():Int{
        if(_busState.value.busList.isEmpty()) return 0
        else return _busState.value.busList.get(0).idx
    }
    fun applyBus(idx:Int){
        when(checkBus()){
            0-> {
                busUseCases.addBusApply(idx)
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
