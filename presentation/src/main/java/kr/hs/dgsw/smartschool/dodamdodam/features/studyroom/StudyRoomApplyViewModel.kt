package kr.hs.dgsw.smartschool.dodamdodam.features.studyroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.StudyRoomUseCases
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ModifyAppliedStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.place.GetAllPlaceUseCase
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StudyRoomApplyViewModel @Inject constructor(
    private val timeUseCases: TimeUseCases,
    private val studyRoomUseCases: StudyRoomUseCases,
    private val getAllPlaceUseCase: GetAllPlaceUseCase,
) : BaseViewModel() {

    private val _currentTime = MutableLiveData<Int>()
    val currentTime: LiveData<Int> get() = _currentTime

    private val _timeTable = MutableLiveData<List<TimeTable>>()
    val timeTable: LiveData<List<TimeTable>> get() = _timeTable

    private val _getAllTimeState = MutableStateFlow(GetAllTimeState())
    val getAllTimeState: StateFlow<GetAllTimeState> = _getAllTimeState

    private val _getPlaceState = MutableStateFlow(GetPlaceState())
    val getPlaceState: StateFlow<GetPlaceState> = _getPlaceState

    private val _applyStudyRoomState = MutableStateFlow(ApplyStudyRoomState())
    val applyStudyRoomState: StateFlow<ApplyStudyRoomState> = _applyStudyRoomState

    private val _getMyStudyRoomState = MutableStateFlow(GetMyStudyRoomState())
    val getMyStudyRoomState: StateFlow<GetMyStudyRoomState> = _getMyStudyRoomState

    var myStudyRoomList = ArrayList<StudyRoom>()
    val currentCheckPlaces = MutableLiveData<List<Place?>>()

    private val isTimeTableLoading = MutableLiveData(false)
    private val isPlaceListLoading = MutableLiveData(false)
    private val isGetMyStudyRoomLoading = MutableLiveData(false)
    private val isApplyStudRoomyLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isTimeTableLoading, isPlaceListLoading, isGetMyStudyRoomLoading, isApplyStudRoomyLoading)
        getTimeTable()
        getPlace()
        getMyLocation()
    }

    fun setCurrentTime(time: Int) {
        _currentTime.value = time
    }

    fun changeLocation(place: Place) {
        currentTime.value?.apply {
            val currentTimeTable = timeTable.value?.get(this) ?: return

            when {
                currentCheckPlaces.value?.get(this) == null -> {
                    changeLocationRemote(params = ModifyAppliedStudyRoom.Params(StudyRoom(currentTimeTable, place)), currentTimeTable.name)
                }
                currentCheckPlaces.value?.get(this) != place -> {
                    val idx: Int = myStudyRoomList.find { it.timeTableIdx == currentTimeTable.id }?.id ?: return
                    val location = Location(null, null, null, place.id)
                    putLocationRemote(params = PutLocation.Params(idx = idx, location = location), currentTimeTable.name)
                }
                else -> {
                    val idx: Int = myStudyRoomList.find { it.timeTableIdx == currentTimeTable.id }?.id ?: return
                    deleteLocationRemote(idx = idx, currentTimeTable.name)
                }
            }
        } ?: viewEvent(EVENT_NO_TIME)
    }

    private fun changeLocationRemote(params: ModifyAppliedStudyRoom.Params, timeName: String) {
        studyRoomUseCases.modifyAppliedStudyRoom(params).divideResult(
            isApplyStudRoomyLoading,
            { _applyStudyRoomState.value = ApplyStudyRoomState(message = "$timeName 위치 신청 성공") },
            { _applyStudyRoomState.value = ApplyStudyRoomState(error = it ?: "위치 신청에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun putLocationRemote(params: PutLocation.Params, timeName: String) {
        studyRoomUseCases.putLocation(params).divideResult(
            isApplyStudRoomyLoading,
            { _applyStudyRoomState.value = ApplyStudyRoomState(message = "$timeName 위치 수정 성공") },
            { _applyStudyRoomState.value = ApplyStudyRoomState(error = it ?: "위치 수정에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun deleteLocationRemote(idx: Int, timeName: String) {
        studyRoomUseCases.deleteLocation(idx).divideResult(
            isApplyStudRoomyLoading,
            { _applyStudyRoomState.value = ApplyStudyRoomState(message = "$timeName 위치 삭제 성공") },
            { _applyStudyRoomState.value = ApplyStudyRoomState(error = it ?: "위치 삭제에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getPlace() {
        getAllPlaceUseCase(Unit).divideResult(
            isGetMyStudyRoomLoading,
            { _getPlaceState.value = GetPlaceState(place = it ?: emptyList()) },
            { _getPlaceState.value = GetPlaceState(error = it ?: "장소를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getTimeTable() {
        timeUseCases.getAllTime(Unit).divideResult(
            isTimeTableLoading,
            { _getAllTimeState.value = GetAllTimeState(timeTable = it ?: emptyList()) },
            { _getAllTimeState.value = GetAllTimeState(error = it ?: "시간을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getMyLocation() {
        val today = LocalDate.now().toString()
        studyRoomUseCases.getMyStudyRoom(today).divideResult(
            isGetMyStudyRoomLoading,
            { _getMyStudyRoomState.value = GetMyStudyRoomState(myLocations = it ?: emptyList()) },
            { _getMyStudyRoomState.value = GetMyStudyRoomState(error = it ?: "자신의 위치를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun setTimeTable(timeTable: List<TimeTable>) {
        _timeTable.value = timeTable
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
        const val EVENT_NO_TIME = 1
    }
}