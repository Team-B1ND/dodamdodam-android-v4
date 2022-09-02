package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetYouTubeVideo @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<String, YoutubeVideo>() {

    override fun invoke(params: String): Flow<Resource<YoutubeVideo>> = execute {
        songRepository.getYoutubeVideo(params)
    }

}