package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.SongRemote
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import javax.inject.Inject

class SongDataSource @Inject constructor(
    override val remote: SongRemote,
    override val cache: Any
) : BaseDataSource<SongRemote, Any>() {

    suspend fun getAllowSong(year: Int, month: Int, date: Int): List<VideoYoutubeData> = remote.getAllowSong(year, month, date)

    suspend fun getPendingSong(): List<VideoYoutubeData> = remote.getPendingSong()

    suspend fun getMySong(id: String): List<VideoYoutubeData> = remote.getMySong(id)

    suspend fun postSong(request: SongRequest): String = remote.postSong(request)

    suspend fun postAllowSong(request: SongCheckRequest): String = remote.postAllowSong(request)

    suspend fun postDenySong(request: SongCheckRequest): String = remote.postDenySong(request)
}