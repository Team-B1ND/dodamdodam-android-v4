package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.YouTubeApi
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.YoutubeVideoResponse
import javax.inject.Inject

class YouTubeRemote @Inject constructor(
    override val api: YouTubeApi
) : BaseRemote<YouTubeApi>() {

    suspend fun getYouTubeVideo(content: String, maxResults: Int): YoutubeVideoResponse =
        api.getYouTubeVideo(content = content, maxResults = maxResults)
}
