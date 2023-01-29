package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import kr.hs.dgsw.smartschool.domain.repository.YouTubeRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetYouTubeVideo @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) : UseCase<GetYouTubeVideo.Params, YoutubeVideo>() {

    override fun invoke(params: Params): Flow<Resource<YoutubeVideo>> = execute {
        youTubeRepository.getYoutubeVideo(params.content, params.maxResults)
    }

    data class Params(
        val content: String,
        val maxResults: Int
    )
}
