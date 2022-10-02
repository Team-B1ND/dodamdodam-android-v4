package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.song.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.song.SongRequest

interface SongRepository {

    suspend fun applySong(request: SongRequest): String

    suspend fun deleteSong(id: String): String

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoSongData>

    suspend fun getPendingSong(): List<VideoSongData>

    suspend fun getMySong(id: String): List<VideoSongData>

    suspend fun getMelonChart(): List<MelonChart>
}
