package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.location.GetMyLocationState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.GetMealState
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.GetAllowSongState
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases,
    private val locationUseCases: LocationUseCases,
    private val setUpUseCases: SetUpUseCases,
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    private val _getMealState = MutableStateFlow(GetMealState(isLoading = false))
    val getMealState: StateFlow<GetMealState> = _getMealState

    private val _getMyLocationState = MutableSharedFlow<GetMyLocationState>()
    val getMyLocationState: SharedFlow<GetMyLocationState> = _getMyLocationState

    private val _dataSetUpState = MutableStateFlow(DataSetUpState())
    val dataSetUpState: StateFlow<DataSetUpState> = _dataSetUpState

    private val _getAllowSongState = MutableStateFlow<GetAllowSongState>(GetAllowSongState())
    val getAllowSongState: StateFlow<GetAllowSongState> = _getAllowSongState

    private val isDataSetUpLoading = MutableLiveData(false)
    private val isGetMealLoading = MutableLiveData(false)
    private val isGetMyLocationLoading = MutableLiveData(false)
    private val isGetAllowSongLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isDataSetUpLoading, isGetMealLoading, isGetMyLocationLoading, isGetAllowSongLoading)
        dataSetUp()
        getAllowSong()
    }

    fun getMealList(date: LocalDate) {
        mealUseCases.getAllMeal(GetAllMeal.Params(date.year, date.monthValue)).divideResult(
            isGetMealLoading,
            { _getMealState.value = GetMealState(meal = it ?: emptyList()) },
            { _getMealState.value = GetMealState(error = it ?: "급식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun dataSetUp() {
        setUpUseCases.dataSetUp(Unit).divideResult(
            isDataSetUpLoading,
            { _dataSetUpState.value = DataSetUpState(result = it) },
            { _dataSetUpState.value = DataSetUpState(error = it ?: "데이터를 업데이트 하지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getMyLocation() {
        locationUseCases.getMyLocation(LocalDate.now().toString()).divideResult(
            isGetMyLocationLoading,
            {
                viewModelScope.launch { _getMyLocationState.emit(GetMyLocationState(myLocations = it ?: emptyList())) }
                it?.forEach { placeList -> Log.d("TestTest", "getMyLocation: ${placeList.place?.name}") }
            },
            { viewModelScope.launch { _getMyLocationState.emit(GetMyLocationState(error = it ?: "위치를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }

    private fun getAllowSong() {
        val today = LocalDate.now()
        songUseCases.getAllowSong(
            GetAllowSong.Params(
                year = today.year,
                month = today.monthValue,
                date = today.dayOfMonth,
            )
        ).divideResult(
            isGetAllowSongLoading,
            { songList -> _getAllowSongState.value = GetAllowSongState(songList = songList ?: emptyList()) },
            { message -> _getAllowSongState.value = GetAllowSongState(error = message ?: "기상송을 받아오지 못했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickSongMore() {
        viewEvent(ON_CLICK_SONG_MORE)
    }

    fun onClickMealMore() {
        viewEvent(ON_CLICK_MEAL_MORE)
    }

    companion object {
        const val ON_CLICK_SONG_MORE = 0
        const val ON_CLICK_MEAL_MORE = 1
    }
}
