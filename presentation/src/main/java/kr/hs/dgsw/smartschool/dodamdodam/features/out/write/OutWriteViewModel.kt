package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.out.write.state.ApplyOutGoingState
import kr.hs.dgsw.smartschool.dodamdodam.features.out.write.state.ApplyOutSleepingState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateTimeFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.hourFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.minuteFormat
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import kr.hs.dgsw.smartschool.domain.usecase.out.ApplyOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.ApplyOutSleeping
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class OutWriteViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {

    val isOutGoing = MutableLiveData(true)
    val isOutSleeping = MutableLiveData(false)

    val startOutSleepingDate = MutableLiveData(Date())
    val endOutSleepingDate = MutableLiveData(Date())
    val startOutGoingDate = MutableLiveData(Date())

    val outReason = MutableLiveData<String>()

    private val _applyOutGoingState = MutableStateFlow<ApplyOutGoingState>(ApplyOutGoingState())
    val applyOutGoingState: StateFlow<ApplyOutGoingState> = _applyOutGoingState

    private val _applyOutSleepingState = MutableStateFlow<ApplyOutSleepingState>(ApplyOutSleepingState())
    val applyOutSleepingState: StateFlow<ApplyOutSleepingState> = _applyOutSleepingState

    private val isApplyOutGoingLoading = MutableLiveData(false)
    private val isApplyOutSleepingLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isApplyOutGoingLoading, isApplyOutSleepingLoading)
    }

    fun invalidOutGoing(startDate: Date, endDate: Date) {
        when {
            outReason.value.isNullOrBlank() -> {
                applyError("사유를 입력해주세요")
                return
            }
            startDate.hourFormat().toInt() >= 24 ||
                startDate.minuteFormat().toInt() >= 60 ||
                endDate.hourFormat().toInt() >= 24 ||
                endDate.minuteFormat().toInt() >= 60
            -> {
                applyError("시간을 확인해주세요")
                return
            }
            startDate.before(Date()) -> {
                applyError("현재 시간 이후부터 신청할 수 있습니다")
                return
            }
            startDate.after(endDate) -> {
                applyError("출발 시간은 도착 시간보다 빨라야 합니다")
                return
            }
        }

        applyOutGoing(startDate, endDate)
    }

    fun invalidOutSleeping(startDate: Date, endDate: Date) {
        when {
            outReason.value.isNullOrBlank() -> {
                applyError("사유를 입력해주세요")
                return
            }
            startDate.hourFormat().toInt() >= 24 ||
                startDate.minuteFormat().toInt() >= 60 ||
                endDate.hourFormat().toInt() >= 24 ||
                endDate.minuteFormat().toInt() >= 60
            -> {
                applyError("시간을 확인해주세요")
                return
            }
            startDate.before(Date()) -> {
                applyError("현재 시간 이후부터 신청할 수 있습니다")
                return
            }
            startDate.after(endDate) -> {
                applyError("출발 시간은 도착 시간보다 빨라야 합니다")
                return
            }
        }

        applyOutSleeping(startDate, endDate)
    }

    private fun applyOutGoing(startDate: Date, endDate: Date) {
        outUseCases.applyOutGoing(
            ApplyOutGoing.Params(
                startDate.dateTimeFormat(),
                endDate.dateTimeFormat(),
                outReason.value ?: ""
            )
        ).divideResult(
            isApplyOutGoingLoading,
            { outItem -> _applyOutGoingState.value = ApplyOutGoingState(outItem = outItem ?: OutItem()) },
            { errorMessage -> _applyOutGoingState.value = ApplyOutGoingState(error = errorMessage ?: "외출 신청에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun applyOutSleeping(startDate: Date, endDate: Date) {
        outUseCases.applyOutSleeping(
            ApplyOutSleeping.Params(
                startDate.dateTimeFormat(),
                endDate.dateTimeFormat(),
                outReason.value ?: ""
            )
        ).divideResult(
            isApplyOutSleepingLoading,
            { outItem -> _applyOutSleepingState.value = ApplyOutSleepingState(outItem = outItem ?: OutItem()) },
            { errorMessage -> _applyOutSleepingState.value = ApplyOutSleepingState(error = errorMessage ?: "외박 신청에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun applyError(message: String) {
        EVENT_ON_ERROR = message
        viewEvent(EVENT_ON_ERROR)
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun selectOutGoing() {
        isOutGoing.value = true
        isOutSleeping.value = false
    }

    fun selectOutSleeping() {
        isOutGoing.value = false
        isOutSleeping.value = true
    }

    fun onClickStartOutGoingDate() {
        viewEvent(EVENT_ON_CLICK_START_OUT_GOING_DATE)
    }
    fun onClickStartOutSleepingDate() {
        viewEvent(EVENT_ON_CLICK_START_OUT_SLEEPING_DATE)
    }
    fun onClickEndOutSleepingDate() {
        viewEvent(EVENT_ON_CLICK_END_OUT_SLEEPING_DATE)
    }

    fun onClickApply() {
        viewEvent(EVENT_ON_CLICK_APPLY)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
        const val EVENT_ON_CLICK_START_OUT_GOING_DATE = 1
        const val EVENT_ON_CLICK_START_OUT_SLEEPING_DATE = 2
        const val EVENT_ON_CLICK_END_OUT_SLEEPING_DATE = 3
        const val EVENT_ON_CLICK_APPLY = 4
        var EVENT_ON_ERROR = ""
    }
}
