package kr.hs.dgsw.smartschool.data.network.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.data.util.SongUtils
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class SongRemote @Inject constructor(
    override val api: SongApi,
    private val quality: String
) : BaseRemote<SongApi>() {

    suspend fun applySong(request: SongRequest): String =
        api.applySong(request).message

    suspend fun deleteSong(id: String): String =
        api.deleteSong(id).message

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData> =
        api.getAllowSong(year, month, day).data.map { video -> VideoSongData(video, quality) }

    suspend fun getMySong(id: String): List<VideoSongData> =
        api.getMySong(id).data.map { video -> VideoSongData(video, quality) }

    suspend fun getPendingSong(): List<VideoSongData> =
        api.getPendingSong().data.map { video -> VideoSongData(video, quality) }

    // web crawling -> melon chart 50
    suspend fun getMelonChart(): List<MelonChart> = withContext(Dispatchers.IO) {

        val jsoup = Jsoup.connect(SongUtils.SONG_CHART_URL)
        val doc: Document = jsoup.get()

        // 크롤링 하고자 하는 엘리먼트들을 저장
        val titleElements: Elements = doc
            .select(SongUtils.SONG_TITLE_CSS_QUERY)
        val artistElements: Elements = doc.select(SongUtils.SONG_ARTIST_CSS_QUERY)
        val thumbnailElements: Elements = doc.select(SongUtils.SONG_THUMBNAIL_CSS_QUERY)

        val melonChartList = mutableListOf<MelonChart>()

        for (i in SongUtils.CHART_RANGE) {
            melonChartList.add(
                MelonChart(
                    title = titleElements[i].text(),
                    artist = artistElements[i].text(),
                    thumbnail = thumbnailElements[i].attr(SongUtils.SONG_THUMBNAIL_ATTR_KEY),
                    rank = (i + 1).toString()
                )
            )
        }

        return@withContext melonChartList.toList()
    }
}
