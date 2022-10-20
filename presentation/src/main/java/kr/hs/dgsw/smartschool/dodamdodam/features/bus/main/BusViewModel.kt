package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
) : BaseViewModel() {
    private val _getBusListState = MutableSharedFlow<GetBusListState>()

    val getBusListState: SharedFlow<GetBusListState> = _getBusListState

    val hasBus = MutableLiveData<Boolean>(false)
    var busId : Int = 0

    private val isGetBusLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isGetBusLoading)
    }
    fun getBusList() {
        busUseCases.getBus(Unit).divideResult(
            isGetBusLoading,
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(bus = it, success = "버스를 성공적으로 불러왔습니다.")) } },
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
    fun applyBus(idx: Int) {
        when (busId) {
            0 -> {
                busUseCases.addBusApply(idx).divideResult(
                    isGetBusLoading,
                    { viewModelScope.launch { _getBusListState.emit(GetBusListState(success = it ?: ""))
                    busId = idx} },
                    { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
                ).launchIn(viewModelScope)
            }
            else -> {
                busUseCases.updateBusApply(idx).divideResult(
                    isGetBusLoading,
                    { viewModelScope.launch { _getBusListState.emit(GetBusListState(success = it ?: ""))
                    busId = idx} },
                    { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
                ).launchIn(viewModelScope)
            }
        }
    }
    fun cancelBus(idx: Int) {
        busUseCases.deleteBusApply(idx).divideResult(
            isGetBusLoading,
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(success = it ?: "")) }
            if(busId == idx) busId = 0},
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
}
