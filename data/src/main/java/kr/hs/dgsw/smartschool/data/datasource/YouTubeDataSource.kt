package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.YouTubeRemote
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.YoutubeVideoResponse
import javax.inject.Inject

class YouTubeDataSource @Inject constructor(
    override val remote: YouTubeRemote,
    override val cache: Any
) : BaseDataSource<YouTubeRemote, Any> {

    suspend fun getYouTubeVideo(content: String, maxResults: Int): YoutubeVideoResponse = remote.getYouTubeVideo(content, maxResults)
}
