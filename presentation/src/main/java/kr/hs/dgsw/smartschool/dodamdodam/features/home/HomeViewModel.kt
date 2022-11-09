package kr.hs.dgsw.smartschool.dodamdodam.features.home

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
import kr.hs.dgsw.smartschool.dodamdodam.features.home.state.GetActiveBannerState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.state.GetMealState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.GetAllowSongState
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.state.GetMyStudyRoomState
import kr.hs.dgsw.smartschool.domain.usecase.banner.GetActiveBannerUseCase
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.StudyRoomUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases,
    private val studyRoomUseCases: StudyRoomUseCases,
    private val songUseCases: SongUseCases,
    private val getActiveBannerUseCase: GetActiveBannerUseCase
) : BaseViewModel() {

    private val _getMealState = MutableStateFlow(GetMealState())
    val getMealState: StateFlow<GetMealState> = _getMealState

    private val _getMyStudyRoomState = MutableSharedFlow<GetMyStudyRoomState>()
    val getMyStudyRoomState: SharedFlow<GetMyStudyRoomState> = _getMyStudyRoomState

    private val _getAllowSongState = MutableStateFlow<GetAllowSongState>(GetAllowSongState())
    val getAllowSongState: StateFlow<GetAllowSongState> = _getAllowSongState

    private val _getActiveBannerState = MutableStateFlow<GetActiveBannerState>(GetActiveBannerState())
    val getActiveBannerState: StateFlow<GetActiveBannerState> = _getActiveBannerState

    private val isDataSetUpLoading = MutableLiveData(false)
    private val isGetMealLoading = MutableLiveData(false)
    private val isGetMyStudyRoomLoading = MutableLiveData(false)
    private val isGetAllowSongLoading = MutableLiveData(false)
    private val isGetActiveBannerLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isDataSetUpLoading, isGetMealLoading, isGetMyStudyRoomLoading, isGetAllowSongLoading)
    }

    fun getMeal(date: LocalDate) {
        mealUseCases.getMeal(GetMeal.Params(date.year, date.monthValue, date.dayOfMonth)).divideResult(
            isGetMealLoading,
            { _getMealState.value = GetMealState(meal = it, isUpdate = true) },
            { _getMealState.value = GetMealState(error = it ?: "급식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getActiveBanner() {
        getActiveBannerUseCase(Unit).divideResult(
            isGetActiveBannerLoading,
            { _getActiveBannerState.value = GetActiveBannerState(it ?: emptyList()) },
            { _getActiveBannerState.value = GetActiveBannerState(error = it ?: "배너를 받아올 수 없습니다.") }
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
            },
            { viewModelScope.launch { _getMyStudyRoomState.emit(GetMyStudyRoomState(error = it ?: "위치를 받아오지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun getAllowSong() {
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

    fun onClickOut() {
        viewEvent(ON_CLICK_OUT)
    }

    fun onClickItMap() {
        viewEvent(ON_CLICK_ITMAP)
    }

    fun onClickLost() {
        viewEvent(ON_CLICK_LOST)
    }

    fun onClickSchedule() {
        viewEvent(ON_CLICK_SCHEDULE)
    }

    companion object {
        const val ON_CLICK_SONG_MORE = 0
        const val ON_CLICK_MEAL_MORE = 1
        const val ON_CLICK_OUT = 2
        const val ON_CLICK_ITMAP = 3
        const val ON_CLICK_LOST = 4
        const val ON_CLICK_SCHEDULE = 5
    }
}
