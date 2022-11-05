package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.request.bus.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
) : BaseViewModel() {
    private val _getBusListState = MutableSharedFlow<GetBusListState>()
    private val _busTaskState = MutableSharedFlow<BusTaskState>()

    val getBusListState: SharedFlow<GetBusListState> = _getBusListState
    val busTaskState: SharedFlow<BusTaskState> = _busTaskState

    val hasBus = MutableLiveData<Boolean>(false)
    var busId: Int = 0

    private val isGetBusLoading = MutableLiveData<Boolean>()
    private val isDoBusTaskLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isDoBusTaskLoading, isGetBusLoading)
        getMyBus()
    }
    fun getBusList() {
        busUseCases.getBus(Unit).divideResult(
            isGetBusLoading,
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(bus = it, applyBusId = busId)) } },
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
    fun getMyBus() {
        busUseCases.getMyBus(Unit).divideResult(
            isDoBusTaskLoading,
            {
                if (it == null) busId = 0 else busId = it.id
                getBusList()
            },
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "신청한 버스를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
    fun applyBus(idx: Int) {
        when (busId) {
            0 -> {
                busUseCases.addBusApply(idx).divideResult(
                    isDoBusTaskLoading,
                    { message ->
                        viewModelScope.launch {
                            _busTaskState.emit(BusTaskState(success = message ?: "버스 신청에 성공했습니다."))
                            getMyBus()
                        }
                    },
                    { message ->
                        viewModelScope.launch {
                            _busTaskState.emit(BusTaskState(error = message ?: "버스 신청에 실패했습니다."))
                        }
                    }
                ).launchIn(viewModelScope)
            }
            else -> {
                busUseCases.updateBusApply(UpdateBusApplyRequest(busId = idx, originBusId = busId)).divideResult(
                    isDoBusTaskLoading,
                    { message ->
                        viewModelScope.launch {
                            _busTaskState.emit(BusTaskState(success = message ?: "버스 신청 수정에 성공했습니다."))
                            getMyBus()
                        }
                    },
                    { message ->
                        viewModelScope.launch {
                            _busTaskState.emit(BusTaskState(error = message ?: "버스 신청 수정에 실패했습니다."))
                        }
                    }
                ).launchIn(viewModelScope)
            }
        }
    }
    fun cancelBus(idx: Int) {
        busUseCases.deleteBusApply(idx).divideResult(
            isDoBusTaskLoading,
            { message ->
                viewModelScope.launch {
                    _busTaskState.emit(BusTaskState(success = message ?: "버스 신청 취소에 성공했습니다."))
                    getMyBus()
                }
            },
            { message ->
                viewModelScope.launch {
                    _busTaskState.emit(BusTaskState(error = message ?: "버스 신청 취소에 실패했습니다."))
                }
            }
        ).launchIn(viewModelScope)
    }
}
