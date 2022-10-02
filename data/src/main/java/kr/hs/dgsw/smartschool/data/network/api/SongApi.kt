package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.SongData
import kr.hs.dgsw.smartschool.domain.request.song.SongCheckRequest
import kr.hs.dgsw.smartschool.domain.request.song.SongRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SongApi {

    @GET("wakeup-song")
    suspend fun getAllowSong(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("date") date: Int
    ): Response<SongData>

    @GET("wakeup-song/pending")
    suspend fun getPendingSong(): Response<SongData>

    @GET("wakeup-song/user")
    suspend fun getMySong(
        @Query("id") id: String
    ): Response<SongData>

    @POST("wakeup-song")
    suspend fun postSong(
        @Body request: SongRequest
    ): Response<Any>

    @POST("wakeup-song/allow")
    suspend fun postAllowSong(
        @Body request: SongCheckRequest
    ): Response<Any>

    @POST("wakeup-song/cancel")
    suspend fun postDenySong(
        @Body request: SongCheckRequest
    ): Response<Any>
}
