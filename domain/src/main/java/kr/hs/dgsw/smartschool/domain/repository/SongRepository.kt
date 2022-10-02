package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.request.song.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.song.SongRequest

interface SongRepository {

    suspend fun getAllowSong(year: Int, month: Int, date: Int): List<VideoYoutubeData>

    suspend fun getPendingSong(): List<VideoYoutubeData>

    suspend fun getMySong(id: String): List<VideoYoutubeData>

    suspend fun postSong(request: SongRequest): String

    suspend fun postAllowSong(request: SongCheckRequest): String

    suspend fun postDenySong(request: SongCheckRequest): String

    suspend fun getMelonChart(): List<MelonChart>
}
