package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.song.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.song.SongRequest
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class SongRemote @Inject constructor(
    override val api: SongApi,
    private val quality: String
) : BaseRemote<SongApi>() {

    private val melonRange: IntRange = IntRange(0, 49)

    suspend fun getAllowSong(year: Int, month: Int, date: Int): List<VideoYoutubeData> =
        api.getAllowSong(year, month, date).data.allow?.map { video -> VideoYoutubeData(video, quality) } ?: emptyList()

    suspend fun getPendingSong(): List<VideoYoutubeData> =
        api.getPendingSong().data.pending?.map { video -> VideoYoutubeData(video, quality) } ?: emptyList()

    suspend fun getMySong(id: String): List<VideoYoutubeData> =
        api.getMySong(id).data.videos?.map { video -> VideoYoutubeData(video, quality) } ?: emptyList()

    suspend fun postSong(request: SongRequest): String =
        api.postSong(request).message

    suspend fun postAllowSong(request: SongCheckRequest): String =
        api.postAllowSong(request).message

    suspend fun postDenySong(request: SongCheckRequest): String =
        api.postDenySong(request).message

    // web crawling -> melon chart 50
    suspend fun getMelonChart(): List<MelonChart> = withContext(Dispatchers.IO) {
        val jsoup = Jsoup.connect("https://www.melon.com/chart/")
        val doc: Document = jsoup.get()
        // 크롤링 하고자 하는 엘리먼트들을 저장
        val titleElements: Elements = doc
            .select("#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank01 > span > a")
        val artistElements: Elements = doc.select("#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank02 > a")
        val thumbnailElements: Elements = doc.select("#lst50 > td:nth-child(4) > div > a > img")

        val melonChartList = mutableListOf<MelonChart>()

        for (i in melonRange) {
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

        return@withContext melonChartList.toList()
    }
}
