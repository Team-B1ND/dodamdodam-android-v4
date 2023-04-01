package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.SongDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.mapper.toRequest
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart
import kr.hs.dgsw.smartschool.domain.param.song.SongParam
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val songDataSource: SongDataSource
) : SongRepository {

    override suspend fun applySong(request: SongParam): String {
        return songDataSource.applySong(request.toRequest())
    }

    override suspend fun deleteSong(id: Int): String {
        return songDataSource.deleteSong(id)
    }

    override suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData> {
        return songDataSource.getAllowSong(year, month, day).map { videoSongDataResponse -> videoSongDataResponse.toModel() }
    }

    override suspend fun getPendingSong(): List<VideoSongData> {
        return songDataSource.getPendingSong().map { videoSongDataResponse -> videoSongDataResponse.toModel() }
    }

    override suspend fun getMySong(): List<VideoSongData> {
        return songDataSource.getMySong().map { videoSongDataResponse -> videoSongDataResponse.toModel() }
    }

    override suspend fun getMelonChart(): List<SongChart> {
        return songDataSource.getMelonChart().map { songChartResponse -> songChartResponse.toModel() }
    }
}
