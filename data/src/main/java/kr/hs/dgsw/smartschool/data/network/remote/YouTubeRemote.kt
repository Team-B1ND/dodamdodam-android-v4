package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.YouTubeApi
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import javax.inject.Inject

class YouTubeRemote @Inject constructor(
    override val api: YouTubeApi
) : BaseRemote<YouTubeApi>() {

    suspend fun getYouTubeVideo(content: String): YoutubeVideo =
        api.getYouTubeVideo(content = content)
}
