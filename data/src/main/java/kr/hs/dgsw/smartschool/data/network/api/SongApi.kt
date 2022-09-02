package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.domain.model.song.Song
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SongApi {

    @POST("wakeup-song")
    suspend fun applySong(
        @Body request: SongRequest
    ): Response<Any>

    @DELETE("wakeup-song")
    suspend fun deleteSong(
        @Body id: String
    ): Response<Any>

    @GET("wakeup-song/allowed")
    suspend fun getAllowSong(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<List<Song>>

    @GET("wakeup-song/my")
    suspend fun getMySong(
        @Query("id") id: String
    ): Response<List<Song>>

    @GET("wakeup-song/pending")
    suspend fun getPendingSong(): Response<List<Song>>

    @GET("search")
    suspend fun getYouTubeVideo(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 1,
        @Query("key") key: String = Constants.YOUTUBE_API_KEY,
        @Query("q") content: String
    ): YoutubeVideo
}
