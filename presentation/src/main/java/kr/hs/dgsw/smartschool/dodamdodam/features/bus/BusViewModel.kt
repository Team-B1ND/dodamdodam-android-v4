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
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    private val _busState = MutableStateFlow(BusState(isLoading = false))
    val busState: StateFlow<BusState> = _busState

    val hasBus = MutableLiveData<Boolean>(false)

    init {
        getBusList()
    }

    private fun getBusList(){
        busUseCases.getBus(Unit).divideResult(
            isLoading,
            { _busState.value = BusState(busList = it ?: emptyList<BusByDate>()) },
            { _busState.value = BusState(error = it ?: "버스를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }
    private fun getMyBus(){
        val list : Flow<Resource<List<Bus>>> = busUseCases.getMyBus(Unit)
    }
    fun applyBus(idx:Int){
        busUseCases.addBusApply(idx)
    }
    fun cancelBus(idx:Int){
        busUseCases.deleteBusApply(idx)
    }
}
