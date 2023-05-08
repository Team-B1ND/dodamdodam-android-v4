package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.write.state.ApplyEveningStudyState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateTimeFormat
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.usecase.eveningstudy.ApplyEveningStudy
import kr.hs.dgsw.smartschool.domain.usecase.eveningstudy.EveningStudyUseCases
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class EveningStudyWriteViewModel @Inject constructor(
    private val eveningStudyUseCases: EveningStudyUseCases
) : BaseViewModel() {

    val startEveningStudyDate = MutableLiveData(Date())
    val endEveningStudyDate = MutableLiveData(Date())
    val eveningStudyReason = MutableLiveData<String>()
    val eveningStudyPhoneReason = MutableLiveData<String>()

    // val isModifyEveningStudy = MutableLiveData(false)
    val isNeedPhone = MutableLiveData(false)
    val id = MutableLiveData<Int>()
    val placeId = MutableLiveData<Int>()
    val placeName = MutableLiveData<String>()

    private val isApplyEveningStudyLoading = MutableLiveData(false)
    private val _applyEveningStudyState = MutableStateFlow<ApplyEveningStudyState>(ApplyEveningStudyState())
    val applyEveningStudyState: StateFlow<ApplyEveningStudyState> = _applyEveningStudyState

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickEveningStudyWrite() {
        viewEvent(ON_CLICK_EVENING_STUDY_WRITE)
    }

    fun onClickStartEveningStudyDate() {
        viewEvent(ON_CLICK_START_EVENING_STUDY_DATE)
    }

    fun onClickEndEveningStudyDate() {
        viewEvent(ON_CLICK_END_EVENING_STUDY_DATE)
    }

    fun onClickStudyRoom() {
        viewEvent(ON_CLICK_STUDY_ROOM)
    }

    fun setEveningStudyPlace(place: Place?) {
        this.placeId.value = place?.id
        this.placeName.value = place?.name
    }

    private fun applyError(message: String) {
        ON_ERROR = message
        viewEvent(ON_ERROR)
    }

    private fun applyEveningStudy(startDate: Date, endDate: Date) {
        eveningStudyUseCases.applyEveningStudy(
            ApplyEveningStudy.Params(
                startDate.dateTimeFormat(),
                endDate.dateTimeFormat(),
                isNeedPhone.value ?: false,
                placeId.value ?: return,
                eveningStudyReason.value ?: "",
                eveningStudyPhoneReason.value ?: ""
            )
        ).divideResult(
            isApplyEveningStudyLoading,
            { eveningStudyItem -> _applyEveningStudyState.value = ApplyEveningStudyState(eveningStudyItem = eveningStudyItem ?: EveningStudyItem()) },
            { errorMessage -> _applyEveningStudyState.value = ApplyEveningStudyState(error = errorMessage ?: "외박 신청에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

//    private fun modifyEveningStudy(startDate: Date, endDate: Date) {
//
//        eveningStudyUseCases.modifyEveningStudy(
//            ModifyEveningStudy.Params(
//                startDate.dateTimeFormat(),
//                endDate.dateTimeFormat(),
//                eveningStudyReason.value ?: return,
//                id.value ?: return,
//                isNeedPhone.value ?: return,
//                eveningStudyPhoneReason.value ?: return
//            )
//        ).divideResult(
//            isApplyEveningStudyLoading,
//            { eveningStudyItem -> _applyEveningStudyState.value = ApplyEveningStudyState(eveningStudyItem = eveningStudyItem ?: EveningStudyItem()) },
//            { errorMessage -> _applyEveningStudyState.value = ApplyEveningStudyState(error = errorMessage ?: "외출 수정에 실패하였습니다.") }
//        ).launchIn(viewModelScope)
//    }

    fun invalidEveningStudy(startDate: Date?, endDate: Date?) {
        when {
            eveningStudyReason.value.isNullOrBlank() -> {
                applyError("사유를 입력해주세요")
                return
            }
            isNeedPhone.value == true && eveningStudyPhoneReason.value.isNullOrBlank() -> {
                applyError("휴대폰 사용 사유를 입력해주세요")
                return
            }
            TimeUnit.DAYS.convert(endDate!!.time - startDate!!.time, TimeUnit.MILLISECONDS) > 14 -> {
                applyError("최대 2주까지만 선택 가능합니다")
                return
            }
            startEveningStudyDate == null -> {
                applyError("시작 날짜를 입력해주세요")
                return
            }
            endEveningStudyDate == null -> {
                applyError("종료 날짜를 입력해주세요")
                return
            }
            startDate!!.before(Date()) -> {
                applyError("현재 시간 이후부터 신청할 수 있습니다")
                return
            }
            startDate!!.after(endDate) -> {
                applyError("시작 날짜은 종료 날짜보다 빨라야 합니다")
                return
            }
        }
        // if (isModifyEveningStudy.value == false) applyEveningStudy(startDate!!, endDate!!) else modifyEveningStudy(startDate!!, endDate!!)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_EVENING_STUDY_WRITE = 1
        const val ON_CLICK_START_EVENING_STUDY_DATE = 2
        const val ON_CLICK_END_EVENING_STUDY_DATE = 3
        const val ON_CLICK_STUDY_ROOM = 4
        var ON_ERROR = ""
    }
}
