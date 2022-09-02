package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("search")
    suspend fun getYouTubeVideo(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 1,
        @Query("key") key: String = Constants.YOUTUBE_API_KEY,
        @Query("q") content: String
    ): YoutubeVideo

}