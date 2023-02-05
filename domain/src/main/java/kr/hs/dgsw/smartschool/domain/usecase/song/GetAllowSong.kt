package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllowSong @Inject constructor(
    private val songRepository: SongRepository
) : UseCase<GetAllowSong.Params, List<VideoSongData>>() {

    override fun invoke(params: Params): Flow<Resource<List<VideoSongData>>> = execute {
        songRepository.getAllowSong(params.year, params.month, params.day)
    }

    data class Params(
        val year: Int,
        val month: Int,
        val day: Int
    )
}
