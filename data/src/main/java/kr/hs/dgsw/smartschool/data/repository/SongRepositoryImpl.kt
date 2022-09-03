package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.SongDataSource
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val songDataSource: SongDataSource
) : SongRepository {

    override suspend fun applySong(request: SongRequest): String {
        return songDataSource.applySong(request)
    }

    override suspend fun deleteSong(id: String): String {
        return songDataSource.deleteSong(id)
    }

    override suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData> {
        return songDataSource.getAllowSong(year, month, day)
    }

    override suspend fun getPendingSong(): List<VideoSongData> {
        return songDataSource.getPendingSong()
    }

    override suspend fun getMySong(id: String): List<VideoSongData> {
        return songDataSource.getMySong(id)
    }

    override suspend fun getMelonChart(): List<MelonChart> {
        return songDataSource.getMelonChart()
    }
}
