package kr.hs.dgsw.smartschool.dodamdodam.features.out

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
import kr.hs.dgsw.smartschool.dodamdodam.features.out.state.DeleteOutGoingState
import kr.hs.dgsw.smartschool.dodamdodam.features.out.state.DeleteOutSleepingState
import kr.hs.dgsw.smartschool.dodamdodam.features.out.state.GetOutState
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OutViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {

    private val _getOutState = MutableSharedFlow<GetOutState>()
    val getOutState: SharedFlow<GetOutState> = _getOutState

    private val _deleteOutGoingState = MutableStateFlow<DeleteOutGoingState>(DeleteOutGoingState())
    val deleteOutGoingState: StateFlow<DeleteOutGoingState> = _deleteOutGoingState

    private val _deleteOutSleepingState = MutableStateFlow<DeleteOutSleepingState>(DeleteOutSleepingState())
    val deleteOutSleepingState: StateFlow<DeleteOutSleepingState> = _deleteOutSleepingState

    private val isGetOutLoading = MutableLiveData(false)
    private val isDeleteOutGoingLoading = MutableLiveData(false)
    private val isDeleteOutSleepingLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetOutLoading, isDeleteOutGoingLoading, isDeleteOutSleepingLoading)
    }

    fun getMyOutApplies() {
        outUseCases.getOut(LocalDate.now().toString()).divideResult(
            isGetOutLoading,
            { outList ->
                viewModelScope.launch {
                    outList?.let {
                        if (outList.isEmpty()) _getOutState.emit(GetOutState(isEmptyList = true))
                        else _getOutState.emit(GetOutState(outList = it))
                    } ?: _getOutState.emit(GetOutState(isEmptyList = true))
                }
            },
            { error -> viewModelScope.launch { _getOutState.emit(GetOutState(error = error ?: "외출 외박을 받아올 수 없습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun deleteOutGoing(idx: Int) {
        outUseCases.deleteOutGoing(idx).divideResult(
            isDeleteOutGoingLoading,
            { message -> _deleteOutGoingState.value = DeleteOutGoingState(message = message ?: "외출 삭제에 성공했습니다.") },
            { error -> _deleteOutGoingState.value = DeleteOutGoingState(error = error ?: "외출 삭제에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun deleteOutSleeping(idx: Int) {
        outUseCases.deleteOutSleeping(idx).divideResult(
            isDeleteOutSleepingLoading,
            { message -> _deleteOutSleepingState.value = DeleteOutSleepingState(message = message ?: "외박 삭제에 성공했습니다.") },
            { error -> _deleteOutSleepingState.value = DeleteOutSleepingState(error = error ?: "외박 삭제에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickOutWrite() {
        viewEvent(ON_CLICK_OUT_WRITE)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_OUT_WRITE = 1
    }
}
