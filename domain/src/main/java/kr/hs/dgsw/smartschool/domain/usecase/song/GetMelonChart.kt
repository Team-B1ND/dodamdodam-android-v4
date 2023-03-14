package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMelonChart @Inject constructor(
    private val songRepository: SongRepository
) : NoParamUseCase<List<SongChart>>() {

    override fun invoke(): Flow<Resource<List<SongChart>>> = execute {
        songRepository.getMelonChart()
    }
}
