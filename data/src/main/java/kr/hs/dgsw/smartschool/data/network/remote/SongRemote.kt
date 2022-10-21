package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart
import kr.hs.dgsw.smartschool.domain.request.song.SongRequest
import javax.inject.Inject

class SongRemote @Inject constructor(
    override val api: SongApi,
    private val quality: String
) : BaseRemote<SongApi>() {

    suspend fun applySong(request: SongRequest): String =
        api.applySong(request).message

    suspend fun deleteSong(id: Int): String =
        api.deleteSong(id).message

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData> =
        api.getAllowSong(year, month, day).data.map { video -> VideoSongData(video, quality) }

    suspend fun getMySong(): List<VideoSongData> =
        api.getMySong().data.map { video -> VideoSongData(video, quality) }

    suspend fun getPendingSong(): List<VideoSongData> =
        api.getPendingSong().data.map { video -> VideoSongData(video, quality) }

    suspend fun getSongChart(): List<SongChart> =
        api.getSongChart().data

}
