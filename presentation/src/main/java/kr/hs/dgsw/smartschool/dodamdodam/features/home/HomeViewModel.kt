package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.location.GetMyLocationState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.GetMealState
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.GetAllowSongState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.monthFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearFormat
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import java.time.LocalDate
import java.util.*
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

    private val _getMyLocationState = MutableStateFlow(GetMyLocationState())
    val getMyLocationState: StateFlow<GetMyLocationState> = _getMyLocationState

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
                _getMyLocationState.value = GetMyLocationState(myLocations = it ?: emptyList())
                it?.forEach { placeList -> Log.d("TestTest", "getMyLocation: ${placeList.place?.name}") }
            },
            { _getMyLocationState.value = GetMyLocationState(error = it?: "위치를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getAllowSong() {
        val today = Date()
        /*songUseCases.getAllowSong(GetAllowSong.Params(
            year = today.yearFormat().toInt(),
            month = today.monthFormat().toInt(),
            date = today.monthFormat().toInt(),
        )).divideResult(
            isGetAllowSongLoading,
            { songList -> _getAllowSongState.value = GetAllowSongState(songList = songList ?: emptyList()) },
            { message -> _getAllowSongState.value = GetAllowSongState(error = message ?: "기상송을 받아오지 못했습니다.")}
        ).launchIn(viewModelScope)*/

        songUseCases.getAllowSong(GetAllowSong.Params(
            year = 2022,
            month = 6,
            date = 13,
        )).divideResult(
            isGetAllowSongLoading,
            { songList -> _getAllowSongState.value = GetAllowSongState(songList = songList ?: emptyList()) },
            { message -> _getAllowSongState.value = GetAllowSongState(error = message ?: "기상송을 받아오지 못했습니다.")}
        ).launchIn(viewModelScope)
    }

    fun onClickSongMore() {
        viewEvent(ON_CLICK_SONG_MORE)
    }

    fun onClickMealMore() {
        viewEvent(ON_CLICK_MEAL_MORE)
    }

    fun onClickMenu() {
        viewEvent(ON_CLICK_MENU)
    }

    companion object {
        const val ON_CLICK_SONG_MORE = 0
        const val ON_CLICK_MEAL_MORE = 1
        const val ON_CLICK_MENU = 2
    }
}