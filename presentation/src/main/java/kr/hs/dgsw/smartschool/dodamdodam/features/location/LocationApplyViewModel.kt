package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.usecase.location.GetMyLocation
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.place.GetAllPlaceUseCase
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LocationApplyViewModel @Inject constructor(
    private val timeUseCases: TimeUseCases,
    private val getAllPlaceUseCase: GetAllPlaceUseCase,
    private val locationUseCases: LocationUseCases
) : BaseViewModel() {

    private val _currentTime = MutableLiveData<Int>()
    val currentTime: LiveData<Int> get() = _currentTime

    private val _getAllTimeState = MutableStateFlow<GetAllTimeState>(GetAllTimeState())
    val getAllTimeState: StateFlow<GetAllTimeState> = _getAllTimeState

    private val _getPlaceState = MutableStateFlow<GetPlaceState>(GetPlaceState())
    val getPlaceState: StateFlow<GetPlaceState> = _getPlaceState

    private val isTimeTableLoading = MutableLiveData(false)
    private val isPlaceListLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isTimeTableLoading, isPlaceListLoading)
        getTimeTable()
        getPlace()
    }

    fun setCurrentTime(time: Int) {
        _currentTime.value = time
    }

    private fun getPlace() {
        getAllPlaceUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isPlaceListLoading.value = false
                    _getPlaceState.value = GetPlaceState(place = result.data ?: emptyList())
                }
                is Resource.Loading -> isPlaceListLoading.value = true
                is Resource.Error ->  {
                    isTimeTableLoading.value = false
                    _getPlaceState.value = GetPlaceState(error = result.message ?: "장소를 받아오지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTimeTable() {
        timeUseCases.getAllTime().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isTimeTableLoading.value = false
                    _getAllTimeState.value = GetAllTimeState(timeTable = result.data ?: emptyList())
                }
                is Resource.Loading -> isTimeTableLoading.value = true
                is Resource.Error -> {
                    isTimeTableLoading.value = false
                    _getAllTimeState.value = GetAllTimeState(error = result.message ?: "시간을 받아오지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeLocation(place: Place) {

    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }

}