package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SongApplyViewModel @Inject constructor(
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    val applyUrl = MutableLiveData<String>()
    var errorMessage = ""

    private val isApplySongLoading = MutableLiveData(false)
    val melonChartList = MutableLiveData<List<MelonChart>>()

    init {
        combineLoadingVariable(isApplySongLoading)
        CoroutineScope(Dispatchers.Main).launch {
            getMelonChart()
        }
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun checkUrl() {
        //case 1) https://www.youtube.com/watch?v=Tn25KXWIeqQ
        //case 2) https://youtu.be/o_nxIQTM_B0
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
                val youtubeUrl = "https://www.youtube.com/watch?v=" + applyUrl.value?.replace(mYoutube, "")
                applyWakeUpSong(youtubeUrl)
            }
            else -> {
                errorMessage = "옳바른 형식의 URL을 입력해주세요!"
                viewEvent(EVENT_ON_URL_ERROR)
            }
        }
    }

    private suspend fun getMelonChart() = withContext(Dispatchers.IO) {
        try {
            val jsoup = Jsoup.connect("https://www.melon.com/chart/")
            val doc: Document = jsoup.get()
            // 크롤링 하고자 하는 엘리먼트들을 저장
            val titleElements: Elements = doc
                .select("#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank01 > span > a")
            val artistElements: Elements = doc.select("#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank02 > a")
            val thumbnailElements: Elements = doc.select("#lst50 > td:nth-child(4) > div > a > img")

            val melonChartList = mutableListOf<MelonChart>()
            for (i in 0..49) {
                Log.d("melon", titleElements[i].text())
                melonChartList.add(
                    MelonChart(
                        title = titleElements[i].text(),
                        artist = artistElements[i].text(),
                        thumbnail = thumbnailElements[i].attr("src"),
                        rank = (i + 1).toString()
                    )
                )
            }
            this@SongApplyViewModel.melonChartList.postValue(melonChartList)
        } catch (e : IOException) {}
    }

    private fun applyWakeUpSong(url: String) {
        songUseCases.postSong(url).divideResult(
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