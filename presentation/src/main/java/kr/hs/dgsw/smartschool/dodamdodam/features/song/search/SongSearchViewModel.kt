package kr.hs.dgsw.smartschool.dodamdodam.features.song.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.ApplySongState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.state.GetYoutubeVideoState
import kr.hs.dgsw.smartschool.domain.usecase.song.GetYouTubeVideo
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import javax.inject.Inject

@HiltViewModel
class SongSearchViewModel @Inject constructor(
    private val songUseCases: SongUseCases,

) : BaseViewModel() {

    val keyword = MutableLiveData<String>()

    private val isGetYoutubeResultLoading = MutableLiveData(false)
    private val isApplySongLoading = MutableLiveData(false)

    private val _getYoutubeVideoState = MutableStateFlow(GetYoutubeVideoState())
    val getYoutubeVideoState: StateFlow<GetYoutubeVideoState> = _getYoutubeVideoState

    private val _applySongState = MutableStateFlow(ApplySongState())
    val applySongState: StateFlow<ApplySongState> = _applySongState

    init {
        combineLoadingVariable(isGetYoutubeResultLoading, isApplySongLoading)
    }

    fun getYoutubeResult() {
        songUseCases.getYouTubeVideo(GetYouTubeVideo.Params(keyword.value ?: return, 20)).divideResult(
            isGetYoutubeResultLoading,
            { result -> _getYoutubeVideoState.value = GetYoutubeVideoState(youtubeVideo = result) },
            { error -> _getYoutubeVideoState.value = GetYoutubeVideoState(error = error ?: "영상을 가져오지 못했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun applySong(url: String) {
        songUseCases.applySong(url).divideResult(
            isApplySongLoading,
            { result -> _applySongState.value = ApplySongState(message = result ?: "기상송 신청에 성공했습니다.") },
            { error -> _applySongState.value = ApplySongState(error = error ?: "기상송 신청에 실패했습니다.") },
        ).launchIn(viewModelScope)
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }
}
