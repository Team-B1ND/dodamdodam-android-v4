package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.SongRemote
import kr.hs.dgsw.smartschool.data.network.response.song.VideoSongDataResponse
import kr.hs.dgsw.smartschool.data.network.response.song.melon.SongChartResponse
import javax.inject.Inject

class SongDataSource @Inject constructor(
    override val remote: SongRemote,
    override val cache: Any
) : BaseDataSource<SongRemote, Any> {

    suspend fun applySong(videoUrl: String): String = remote.applySong(videoUrl)

    suspend fun deleteSong(id: Int): String = remote.deleteSong(id)

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongDataResponse> = remote.getAllowSong(year, month, day)

    suspend fun getPendingSong(): List<VideoSongDataResponse> = remote.getPendingSong()

    suspend fun getMySong(): List<VideoSongDataResponse> = remote.getMySong()

    suspend fun getMelonChart(): List<SongChartResponse> = remote.getSongChart()
}
