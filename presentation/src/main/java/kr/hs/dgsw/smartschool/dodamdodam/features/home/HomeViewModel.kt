package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.util.Log
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
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.GetMealState
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.GetAllowSongState
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.state.GetMyStudyRoomState
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.StudyRoomUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases,
    private val studyRoomUseCases: StudyRoomUseCases,
    private val setUpUseCases: SetUpUseCases,
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    private val _getMealState = MutableStateFlow(GetMealState())
    val getMealState: StateFlow<GetMealState> = _getMealState

    private val _getMyStudyRoomState = MutableSharedFlow<GetMyStudyRoomState>()
    val getMyStudyRoomState: SharedFlow<GetMyStudyRoomState> = _getMyStudyRoomState

    private val _dataSetUpState = MutableSharedFlow<DataSetUpState>()
    val dataSetUpState: SharedFlow<DataSetUpState> = _dataSetUpState

    private val _getAllowSongState = MutableStateFlow<GetAllowSongState>(GetAllowSongState())
    val getAllowSongState: StateFlow<GetAllowSongState> = _getAllowSongState

    private val isDataSetUpLoading = MutableLiveData(false)
    private val isGetMealLoading = MutableLiveData(false)
    private val isGetMyStudyRoomLoading = MutableLiveData(false)
    private val isGetAllowSongLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isDataSetUpLoading, isGetMealLoading, isGetMyStudyRoomLoading, isGetAllowSongLoading)
        dataSetUp()
        // getAllowSong()
    }

    fun getMeal(date: LocalDate) {
        mealUseCases.getMeal(GetMeal.Params(date.year, date.monthValue, date.dayOfMonth)).divideResult(
            isGetMealLoading,
            { _getMealState.value = GetMealState(meal = it, isUpdate = true) },
            { _getMealState.value = GetMealState(error = it ?: "급식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun dataSetUp() {
        setUpUseCases.dataSetUp(Unit).divideResult(
            isDataSetUpLoading,
            { viewModelScope.launch { _dataSetUpState.emit(DataSetUpState(result = it ?: "데이터 업데이트에 성공하였습니다.")) } },
            { viewModelScope.launch { _dataSetUpState.emit(DataSetUpState(error = it ?: "데이터를 업데이트 하지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun getMyStudyRoom() {
        studyRoomUseCases.getMyStudyRoom(Unit).divideResult(
            isGetMyStudyRoomLoading,
            {
                viewModelScope.launch {
                    _getMyStudyRoomState.emit(
                        GetMyStudyRoomState(
                            isUpdate = true,
                            myStudyRooms = it ?: emptyList()
                        )
                    )
                }
                it?.forEach { placeList -> Log.d("TestTest", "getMyLocation: ${placeList.place?.name}") }
            },
            { viewModelScope.launch { _getMyStudyRoomState.emit(GetMyStudyRoomState(error = it ?: "위치를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }

    private fun getAllowSong() {
        val today = LocalDate.now()
        songUseCases.getAllowSong(
            GetAllowSong.Params(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
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

    fun onClickOut() {
        viewEvent(ON_CLICK_OUT)
    }

    companion object {
        const val ON_CLICK_SONG_MORE = 0
        const val ON_CLICK_MEAL_MORE = 1
        const val ON_CLICK_OUT = 2
    }
}
