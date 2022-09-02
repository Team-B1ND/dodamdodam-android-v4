package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.song.apply.state.GetMelonChartState
import kr.hs.dgsw.smartschool.dodamdodam.features.song.apply.state.GetYoutubeVideoState
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import javax.inject.Inject

@HiltViewModel
class SongApplyViewModel @Inject constructor(
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    val applyUrl = MutableLiveData<String>()
    var errorMessage = ""

    private val _getMelonChartState = MutableStateFlow<GetMelonChartState>(GetMelonChartState())
    val getMelonChartState: StateFlow<GetMelonChartState> = _getMelonChartState

    private val _getYouTubeVideoState = MutableStateFlow<GetYoutubeVideoState>(GetYoutubeVideoState())
    val getYouTubeVideoState: StateFlow<GetYoutubeVideoState> = _getYouTubeVideoState

    private val isApplySongLoading = MutableLiveData(false)
    private val isMelonChartLoading = MutableLiveData(false)
    private val isGetYoutubeVideo = MutableLiveData(false)

    init {
        combineLoadingVariable(isApplySongLoading, isMelonChartLoading, isGetYoutubeVideo)

        CoroutineScope(Dispatchers.Main).launch {
            getMelonChart()
        }
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun checkUrl() {
        // case 1) https://www.youtube.com/watch?v=Tn25KXWIeqQ
        // case 2) https://youtu.be/o_nxIQTM_B0
        val youtube = "https://www.youtube.com/watch?v="
        val mYoutube = "https://youtu.be/"

        when {
            applyUrl.value.isNullOrBlank() -> {
                errorMessage = "URL을 입력해주세요!"
                viewEvent(EVENT_ON_URL_ERROR)
                return
            }
            applyUrl.value?.startsWith(youtube) == true -> {
                applyWakeUpSong(applyUrl.value ?: "")
            }
            applyUrl.value?.startsWith(mYoutube) == true -> {
                val youtubeUrl = youtube + applyUrl.value?.replace(mYoutube, "")
                applyWakeUpSong(youtubeUrl)
            }
            else -> {
                errorMessage = "옳바른 형식의 URL을 입력해주세요!"
                viewEvent(EVENT_ON_URL_ERROR)
            }
        }
    }

    private fun getMelonChart() {
        songUseCases.getMelonChart(Unit).divideResult(
            isMelonChartLoading,
            { _getMelonChartState.value = GetMelonChartState(melonChartList = it ?: emptyList()) },
            { _getMelonChartState.value = GetMelonChartState(error = it ?: "음원 차트를 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getYouTubeVideo(title: String) {
        songUseCases.getYouTubeVideo(title).divideResult(
            isGetYoutubeVideo,
            { _getYouTubeVideoState.value = GetYoutubeVideoState(youtubeVideo = it) },
            { _getYouTubeVideoState.value = GetYoutubeVideoState(error = it ?: "영상을 받을 수 없습니다.")}
        ).launchIn(viewModelScope)
    }

    fun applyWakeUpSong(url: String) {
        songUseCases.applySong(url).divideResult(
            isApplySongLoading,
            { viewEvent(EVENT_ON_SUCCESS_APPLY) },
            {
                errorMessage = it ?: "기상송 신청에 실패했습니다."
                viewEvent(EVENT_ON_URL_ERROR)
            }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
        const val EVENT_ON_URL_ERROR = 1
        const val EVENT_ON_SUCCESS_APPLY = 2
    }
}
