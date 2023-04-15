package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.YouTubeDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import kr.hs.dgsw.smartschool.domain.repository.YouTubeRepository
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private val youTubeDataSource: YouTubeDataSource
) : YouTubeRepository {

    override suspend fun getYoutubeVideo(content: String, maxResults: Int): YoutubeVideo {
        return youTubeDataSource.getYouTubeVideo(content, maxResults).toModel()
    }
}
