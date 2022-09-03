package kr.hs.dgsw.smartschool.dodamdodam.features.song.youtube

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.ApplySongState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.GetYoutubeVideoState
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import javax.inject.Inject

@HiltViewModel
class YouTubeViewModel @Inject constructor(
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    private val _getYouTubeVideoState = MutableSharedFlow<GetYoutubeVideoState>()
    val getYouTubeVideoState: SharedFlow<GetYoutubeVideoState> = _getYouTubeVideoState

    private val _applySongState = MutableSharedFlow<ApplySongState>()
    val applySongState: SharedFlow<ApplySongState> = _applySongState

    val url = MutableLiveData<String>()
    var errorMessage = ""
    val searchSongTitle = MutableLiveData<String>()

    private val isApplySongLoading = MutableLiveData(false)
    private val isGetYouTubeVideoLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isApplySongLoading, isGetYouTubeVideoLoading)
    }

    fun getYouTubeVideo(title: String) {
        songUseCases.getYouTubeVideo(title).divideResult(
            isGetYouTubeVideoLoading,
            { viewModelScope.launch { _getYouTubeVideoState.emit(GetYoutubeVideoState(youtubeVideo = it)) } },
            { viewModelScope.launch { _getYouTubeVideoState.emit(GetYoutubeVideoState(error = it ?: "영상을 받을 수 없습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun applyWakeUpSong(url: String) {
        songUseCases.applySong(url).divideResult(
            isApplySongLoading,
            { viewModelScope.launch { _applySongState.emit(ApplySongState(it ?: "기상송 신청에 성공했습니다.")) } },
            { viewModelScope.launch { _applySongState.emit(ApplySongState(it ?: "기상송 신청에 실패했습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun checkSearchTitle() {
        if (searchSongTitle.value.isNullOrBlank()) {
            errorMessage = "검색어를 입력해주세요!"
            viewEvent(EVENT_ON_SEARCH_TITLE_ERROR)
            return
        }

        getYouTubeVideo(searchSongTitle.value ?: "아이와 나의 바다")
    }

    fun onClickApply() {
        applyWakeUpSong(url.value ?: "https://www.youtube.com/watch?v=TqIAndOnd74")
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun onClickThumbnail() {
        viewEvent(EVENT_ON_CLICK_THUMBNAIL)
    }

    companion object {
        const val EVENT_ON_SEARCH_TITLE_ERROR = 1
        const val EVENT_ON_CLICK_BACK = 2
        const val EVENT_ON_CLICK_THUMBNAIL = 3
    }
}
