package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import javax.inject.Inject

class SongRemote @Inject constructor(
    override val api: SongApi,
    private val quality: String
): BaseRemote<SongApi>() {

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
}