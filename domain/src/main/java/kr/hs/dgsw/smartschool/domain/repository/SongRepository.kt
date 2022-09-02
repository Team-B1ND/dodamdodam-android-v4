package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.SongRequest

interface SongRepository {

    suspend fun applySong(request: SongRequest): String

    suspend fun deleteSong(id: String): String

    suspend fun getAllowSong(year: Int, month: Int, day: Int): List<VideoYoutubeData>

    suspend fun getPendingSong(): List<VideoYoutubeData>

    suspend fun getMySong(id: String): List<VideoYoutubeData>

    suspend fun getMelonChart(): List<MelonChart>

}
