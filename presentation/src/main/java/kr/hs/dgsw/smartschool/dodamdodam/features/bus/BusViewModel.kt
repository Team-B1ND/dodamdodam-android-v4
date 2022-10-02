package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
) : BaseViewModel() {
    private val _getBusListState = MutableSharedFlow<GetBusListState>()
    private val _getMyBusState = MutableStateFlow(GetMyBusState(isLoading = false))
    private val _addBusApplyState = MutableStateFlow(AddBusApplyState(isLoading = false))
    private val _updateBusApplyState = MutableStateFlow(UpdateBusApplyState(isLoading = false))
    private val _deleteBusApplyState = MutableStateFlow(DeleteBusApplyState(isLoading = false))
    private val _busApplyState = MutableStateFlow(BusApplyState(isLoading = false))

    val busApplyState: StateFlow<BusApplyState> = _busApplyState
    val getBusListState: SharedFlow<GetBusListState> = _getBusListState

    val hasBus = MutableLiveData<Boolean>(false)

    private val isGetMyBusLoading = MutableLiveData<Boolean>()
    private val isGetBusLoading = MutableLiveData<Boolean>()
    private val isAddBusApplyLoading = MutableLiveData<Boolean>()
    private val isUpdateBusApplyLoading = MutableLiveData<Boolean>()
    private val isDeleteBusApplyLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isGetBusLoading, isGetMyBusLoading, isAddBusApplyLoading, isUpdateBusApplyLoading, isDeleteBusApplyLoading)
        getBusList()
    }
    private fun getBusList() {
        getMyBus()
        busUseCases.getBus(Unit).divideResult(
            isGetBusLoading,
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(busList = it ?: emptyList())) } },
            { viewModelScope.launch { _getBusListState.emit(GetBusListState(error = it ?: "버스를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
    private fun getMyBus() {
        busUseCases.getMyBus(Unit).divideResult(
            isGetMyBusLoading,
            {
                _getMyBusState.value = GetMyBusState(busList = it ?: emptyList<Bus>())
                _busApplyState.value = BusApplyState(
                    busId =
                    if (_getMyBusState.value.busList.isNotEmpty()) _getMyBusState.value.busList[0].idx
                    else 0
                )
            },
            {
                _getMyBusState.value = GetMyBusState(error = it ?: "버스를 받아오지 못하였습니다.")
                _busApplyState.value = BusApplyState(error = it ?: "버스를 받아오지 못하였습니다.")
            }
        ).launchIn(viewModelScope)
    }
    fun applyBus(idx: Int) {
        getMyBus()
        when (_busApplyState.value.busId) {
            0 -> {
                busUseCases.addBusApply(idx).divideResult(
                    isAddBusApplyLoading,
                    {
                        _addBusApplyState.value = AddBusApplyState(success = "버스 신청에 성공했습니다.")
                        _busApplyState.value = BusApplyState(busId = idx)
                        getBusList()
                    },
                    {
                        _addBusApplyState.value = AddBusApplyState(error = "버스 신청에 실패했습니다.")
                        getBusList()
                    }
                ).launchIn(viewModelScope)
            }
            else -> {
                busUseCases.updateBusApply(UpdateBusApplyRequest(busIdx = idx.toString(), originBusIdx = _busApplyState.value.busId.toString())).divideResult(
                    isUpdateBusApplyLoading,
                    {
                        _updateBusApplyState.value = UpdateBusApplyState(success = "버스 신청에 성공했습니다.")
                        _busApplyState.value = BusApplyState(busId = idx)
                        getBusList()
                    },
                    {
                        _updateBusApplyState.value = UpdateBusApplyState(error = "버스 신청에 실패했습니다.")
                        getBusList()
                    }
                ).launchIn(viewModelScope)
            }
        }
    }
    fun cancelBus(idx: Int) {
        busUseCases.deleteBusApply(idx).divideResult(
            isDeleteBusApplyLoading,
            {
                _deleteBusApplyState.value = DeleteBusApplyState(success = "정상적으로 버스를 삭제했습니다.")
                _busApplyState.value = BusApplyState(busId = 0)
                getBusList()
            },
            {
                _deleteBusApplyState.value = DeleteBusApplyState(error = "정상적으로 버스를 삭제하는데에 실패하였습니다.")
                getBusList()
            }
        ).launchIn(viewModelScope)
    }
}
