package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart

interface SongRepository {

    suspend fun applySong(videoUrl: String): String

    suspend fun deleteSong(id: Int): String

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData>

    suspend fun getPendingSong(): List<VideoSongData>

    suspend fun getMySong(): List<VideoSongData>

    suspend fun getMelonChart(): List<SongChart>
}
