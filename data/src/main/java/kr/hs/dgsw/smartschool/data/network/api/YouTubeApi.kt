package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.data.util.Key
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET(DodamUrl.SEARCH)
    suspend fun getYouTubeVideo(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int,
        @Query("key") key: String = Key.YOUTUBE_API_KEY,
        @Query("type") type: String = "video",
        @Query("q") content: String
    ): YoutubeVideo
}
