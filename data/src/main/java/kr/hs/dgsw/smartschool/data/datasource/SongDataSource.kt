package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.SongRemote
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import javax.inject.Inject

class SongDataSource @Inject constructor(
    override val remote: SongRemote,
    override val cache: Any
) : BaseDataSource<SongRemote, Any> {

    suspend fun applySong(request: SongRequest): String = remote.applySong(request)

    suspend fun deleteSong(id: String): String = remote.deleteSong(id)

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData> = remote.getAllowSong(year, month, day)

    suspend fun getPendingSong(): List<VideoSongData> = remote.getPendingSong()

    suspend fun getMySong(id: String): List<VideoSongData> = remote.getMySong(id)

    suspend fun getMelonChart(): List<MelonChart> = remote.getMelonChart()
}
