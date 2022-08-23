package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetPendingSong @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<Unit, List<VideoYoutubeData>>() {
    override fun invoke(params: Unit): Flow<Resource<List<VideoYoutubeData>>> = execute {
        songRepository.getPendingSong()
    }
}
