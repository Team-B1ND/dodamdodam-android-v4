package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.data.network.request.song.SongRequest
import kr.hs.dgsw.smartschool.data.network.response.song.VideoSongDataResponse
import kr.hs.dgsw.smartschool.data.network.response.song.melon.SongChartResponse
import javax.inject.Inject

class SongRemote @Inject constructor(
    override val api: SongApi,
    private val quality: String
) : BaseRemote<SongApi>() {

    suspend fun applySong(request: SongRequest): String =
        api.applySong(request).message

    suspend fun deleteSong(id: Int): String =
        api.deleteSong(id).message

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongDataResponse> =
        api.getAllowSong(year, month, day).data.map { video -> VideoSongDataResponse(video, quality) }

    suspend fun getMySong(): List<VideoSongDataResponse> =
        api.getMySong().data.map { video -> VideoSongDataResponse(video, quality) }

    suspend fun getPendingSong(): List<VideoSongDataResponse> =
        api.getPendingSong().data.map { video -> VideoSongDataResponse(video, quality) }

    suspend fun getSongChart(): List<SongChartResponse> =
        api.getSongChart().data
}
