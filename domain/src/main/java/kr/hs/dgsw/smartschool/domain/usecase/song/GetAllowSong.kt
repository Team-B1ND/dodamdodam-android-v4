package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllowSong @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<GetAllowSong.Params, List<VideoYoutubeData>>() {

    override fun invoke(params: Params): Flow<Resource<List<VideoYoutubeData>>> = execute {
        songRepository.getAllowSong(params.year, params.month, params.date)
    }

    data class Params(
        val year: Int,
        val month: Int,
        val date: Int
    )
}
