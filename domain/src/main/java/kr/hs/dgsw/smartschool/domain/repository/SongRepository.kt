package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart
import kr.hs.dgsw.smartschool.domain.request.SongRequest

interface SongRepository {

    suspend fun applySong(request: SongRequest): String

    suspend fun deleteSong(id: String): String

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData>

    suspend fun getPendingSong(): List<VideoSongData>

    suspend fun getMySong(id: String): List<VideoSongData>

    suspend fun getMelonChart(): List<MelonChart>
}
