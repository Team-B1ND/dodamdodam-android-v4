package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.YouTubeRetrofitRemote
import kr.hs.dgsw.smartschool.data.network.api.YouTubeApi
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo

class YouTubeRemote: YouTubeRetrofitRemote<YouTubeApi>()  {

    override val api: YouTubeApi
        get() = createApi(YouTubeApi::class.java)

    suspend fun getYouTubeVideo(content: String): YoutubeVideo =
        api.getYouTubeVideo(content = content)

}