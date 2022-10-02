package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.SongDataSource
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.request.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val songDataSource: SongDataSource
) : SongRepository {
    override suspend fun getAllowSong(year: Int, month: Int, date: Int): List<VideoYoutubeData> {
        return songDataSource.getAllowSong(year, month, date)
    }

    override suspend fun getPendingSong(): List<VideoYoutubeData> {
        return songDataSource.getPendingSong()
    }

    override suspend fun getMySong(id: String): List<VideoYoutubeData> {
        return songDataSource.getMySong(id)
    }

    override suspend fun postSong(request: SongRequest): String {
        return songDataSource.postSong(request)
    }

    override suspend fun postAllowSong(request: SongCheckRequest): String {
        return songDataSource.postAllowSong(request)
    }

    override suspend fun postDenySong(request: SongCheckRequest): String {
        return songDataSource.postDenySong(request)
    }

    override suspend fun getMelonChart(): List<MelonChart> {
        return songDataSource.getMelonChart()
    }
}
