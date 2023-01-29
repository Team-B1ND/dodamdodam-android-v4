package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetPendingSong @Inject constructor(
    private val songRepository: SongRepository
) : NoParamUseCase<List<VideoSongData>>() {

    override fun invoke(): Flow<Resource<List<VideoSongData>>> = execute {
        songRepository.getPendingSong()
    }
}
