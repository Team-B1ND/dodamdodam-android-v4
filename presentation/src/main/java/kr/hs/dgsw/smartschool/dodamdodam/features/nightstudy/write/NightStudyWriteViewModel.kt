package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.write.state.NightStudyWriteState
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.state.GetPlaceState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateTimeFormat
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.usecase.nightstudy.ApplyNightStudy
import kr.hs.dgsw.smartschool.domain.usecase.nightstudy.NightStudyUseCases
import kr.hs.dgsw.smartschool.domain.usecase.place.GetDormitoryPlaceUseCase
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class NightStudyWriteViewModel @Inject constructor(
    private val nightStudyUseCases: NightStudyUseCases,
    private val getDormitoryPlaceUseCase: GetDormitoryPlaceUseCase
) : BaseViewModel() {

    val startNightStudyDate = MutableLiveData(Date())
    val endNightStudyDate = MutableLiveData(Date())
    val nightStudyReason = MutableLiveData<String>()
    val nightStudyPhoneReason = MutableLiveData<String>()

    private val _getPlaceState = MutableSharedFlow<GetPlaceState>()
    val getPlaceState = _getPlaceState.asSharedFlow()

    val isNeedPhone = MutableLiveData(false)
    var placeId = 0
    var placeName = ""

    private val isGetDormitoryStudyRoomLoading = MutableLiveData(false)
    private val isApplyNightStudyLoading = MutableLiveData(false)
    private val _nightStudyWriteState = MutableSharedFlow<NightStudyWriteState>()
    val nightStudyWriteState: SharedFlow<NightStudyWriteState> = _nightStudyWriteState

    init {
        val targetDate = LocalDateTime.now().plusDays(12)
        endNightStudyDate.value = Date.from(targetDate.toInstant(ZoneOffset.MIN))
        getDormitoryPlace()
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickIsNeedPhone() {
        viewEvent(ON_CLICK_IS_NEED_PHONE)
    }

    fun onClickNightStudyWrite() {
        viewEvent(ON_CLICK_NIGHT_STUDY_WRITE)
    }

    fun onClickStartNightStudyDate() {
        viewEvent(ON_CLICK_START_NIGHT_STUDY_DATE)
    }

    fun onClickEndNightStudyDate() {
        viewEvent(ON_CLICK_END_NIGHT_STUDY_DATE)
    }

    fun onClickStudyRoom() {
        viewEvent(ON_CLICK_STUDY_ROOM)
    }

    private fun applyError(message: String) {
        ON_ERROR = message
        viewEvent(ON_ERROR)
    }

    private fun applyNightStudy(id: Int?) {

        nightStudyUseCases.applyNightStudy(
            ApplyNightStudy.Params(
                nightStudyReason.value ?: "",
                endNightStudyDate.value?.dateTimeFormat() ?: return,
                isNeedPhone.value ?: false,
                id ?: return,
                nightStudyPhoneReason.value ?: "",
                startNightStudyDate.value?.dateTimeFormat() ?: return,
            )
        ).divideResult(
            isApplyNightStudyLoading,
            { nightStudyItem -> viewModelScope.launch { _nightStudyWriteState.emit(NightStudyWriteState(nightStudyItem = nightStudyItem ?: NightStudyItem())) } },
            { errorMessage -> viewModelScope.launch { _nightStudyWriteState.emit(NightStudyWriteState(error = errorMessage ?: "외박 신청에 실패했습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun invalidNightStudy(startDate: Date?, endDate: Date?, id: Int?) {
        when {
            nightStudyReason.value.isNullOrBlank() -> {
                applyError("사유를 입력해주세요")
                return
            }
            nightStudyReason.value!!.length < 10 -> {
                applyError("사유는 10자 이상 입력해주세요")
                return
            }
            nightStudyReason.value!!.length > 250 -> {
                applyError("사유는 250자 이하로 입력해주세요")
                return
            }
            isNeedPhone.value == true && nightStudyPhoneReason.value.isNullOrBlank() -> {
                applyError("휴대폰 사용 사유를 입력해주세요")
                return
            }
            isNeedPhone.value == true && nightStudyPhoneReason.value!!.length > 250 -> {
                applyError("휴대폰 사용 사유는 250자 이하로 입력해주세요")
                return
            }
            TimeUnit.DAYS.convert(endDate!!.time - startDate!!.time, TimeUnit.MILLISECONDS) > 13 -> {
                applyError("최대 2주까지만 선택 가능합니다")
                return
            }
            startDate.before(Date(Date().time - 1000 * 60 * 60)) -> {
                applyError("현재 날짜 이후부터 신청할 수 있습니다")
                return
            }
            startDate.after(endDate) || startDate == endDate -> {
                applyError("시작 날짜은 종료 날짜보다 빨라야 합니다")
                return
            }
            id == null -> {
                applyError("심자 장소를 선택해주세요")
                return
            }
            LocalTime.now() > LocalTime.of(16, 30) -> {
                applyError("4시 30분 이후로는 심자 신청이 불가능 합니다")
            }
            else -> applyNightStudy(id)
        }
    }

    fun getDormitoryPlace() {
        getDormitoryPlaceUseCase().divideResult(
            isGetDormitoryStudyRoomLoading,
            { viewModelScope.launch { _getPlaceState.emit(GetPlaceState(place = it ?: emptyList())) } },
            { viewModelScope.launch { GetPlaceState(error = it ?: "장소를 받아오지 못하였습니다.") } }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_NIGHT_STUDY_WRITE = 1
        const val ON_CLICK_START_NIGHT_STUDY_DATE = 2
        const val ON_CLICK_END_NIGHT_STUDY_DATE = 3
        const val ON_CLICK_STUDY_ROOM = 4
        const val ON_CLICK_IS_NEED_PHONE = 5
        var ON_ERROR = ""
    }
}
