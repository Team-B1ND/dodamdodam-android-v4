package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMelonChart @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<Unit, List<MelonChart>>() {
    override fun invoke(params: Unit): Flow<Resource<List<MelonChart>>> = execute {
        songRepository.getMelonChart()
    }
}