package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import kr.hs.dgsw.smartschool.domain.repository.YouTubeRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetYouTubeVideo @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) : BaseUseCase<String, YoutubeVideo>() {

    override fun invoke(params: String): Flow<Resource<YoutubeVideo>> = execute {
        youTubeRepository.getYoutubeVideo(params)
    }

}