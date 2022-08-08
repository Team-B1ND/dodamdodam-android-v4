package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.Time
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.location.PostLocation
import kr.hs.dgsw.smartschool.domain.usecase.location.PutLocation
import kr.hs.dgsw.smartschool.domain.usecase.place.GetAllPlaceUseCase
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LocationApplyViewModel @Inject constructor(
    private val timeUseCases: TimeUseCases,
    private val locationUseCases: LocationUseCases,
    private val getAllPlaceUseCase: GetAllPlaceUseCase,
) : BaseViewModel() {

    private val _currentTime = MutableLiveData<Int>()
    val currentTime: LiveData<Int> get() = _currentTime

    private val _timeTable = MutableLiveData<List<Time>>()
    val timeTable: LiveData<List<Time>> get() = _timeTable

    private val _getAllTimeState = MutableStateFlow(GetAllTimeState())
    val getAllTimeState: StateFlow<GetAllTimeState> = _getAllTimeState

    private val _getPlaceState = MutableStateFlow(GetPlaceState())
    val getPlaceState: StateFlow<GetPlaceState> = _getPlaceState

    private val _applyLocationState = MutableStateFlow(ApplyLocationState())
    val applyLocationState: StateFlow<ApplyLocationState> = _applyLocationState

    private val _getMyLocationState = MutableStateFlow(GetMyLocationState())
    val getMyLocationState: StateFlow<GetMyLocationState> = _getMyLocationState

    var myLocationInfoList = ArrayList<LocationInfo>()
    val currentCheckPlaces = MutableLiveData<List<Place?>>()

    private val isTimeTableLoading = MutableLiveData(false)
    private val isPlaceListLoading = MutableLiveData(false)
    private val isGetMyLocationLoading = MutableLiveData(false)
    private val isApplyLocationLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isTimeTableLoading, isPlaceListLoading, isGetMyLocationLoading, isApplyLocationLoading)
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
                    changeLocationRemote(params = PostLocation.Params(LocationInfo(currentTimeTable, place)), currentTimeTable.name)
                }
                currentCheckPlaces.value?.get(this) != place -> {
                    val idx: Int = myLocationInfoList.find { it.timeTableIdx == currentTimeTable.idx }?.idx ?: return
                    val location = Location(null, null, null, place.idx)
                    putLocationRemote(params = PutLocation.Params(idx = idx, location = location), currentTimeTable.name)
                }
                else -> {
                    val idx: Int = myLocationInfoList.find { it.timeTableIdx == currentTimeTable.idx }?.idx ?: return
                    deleteLocationRemote(idx = idx, currentTimeTable.name)
                }
            }

        } ?: viewEvent(EVENT_NO_TIME)
    }

    private fun changeLocationRemote(params: PostLocation.Params, timeName: String) {
        locationUseCases.postLocation(params).divideResult(
            isApplyLocationLoading,
            { _applyLocationState.value = ApplyLocationState(message = "$timeName 위치 신청 성공") },
            { _applyLocationState.value = ApplyLocationState(error = it ?: "위치 신청에 실패했습니다.")}
        ).launchIn(viewModelScope)
    }

    private fun putLocationRemote(params: PutLocation.Params, timeName: String) {
        locationUseCases.putLocation(params).divideResult(
            isApplyLocationLoading,
            { _applyLocationState.value = ApplyLocationState(message = "$timeName 위치 수정 성공") },
            { _applyLocationState.value = ApplyLocationState(error = it ?: "위치 수정에 실패했습니다.")}
        ).launchIn(viewModelScope)
    }

    private fun deleteLocationRemote(idx: Int, timeName: String) {
        locationUseCases.deleteLocation(idx).divideResult(
            isApplyLocationLoading,
            { _applyLocationState.value = ApplyLocationState(message = "$timeName 위치 삭제 성공") },
            { _applyLocationState.value = ApplyLocationState(error = it ?: "위치 삭제에 실패했습니다.")}
        ).launchIn(viewModelScope)
    }

    private fun getPlace() {
        getAllPlaceUseCase(Unit).divideResult(
            isGetMyLocationLoading,
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
        locationUseCases.getMyLocation(today).divideResult(
            isGetMyLocationLoading,
            { _getMyLocationState.value = GetMyLocationState(myLocations = it ?: emptyList()) },
            { _getMyLocationState.value = GetMyLocationState(error = it ?: "자신의 위치를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun setTimeTable(timeTable: List<Time>) {
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