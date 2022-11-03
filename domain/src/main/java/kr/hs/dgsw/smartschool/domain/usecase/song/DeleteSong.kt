package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteSong @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<Int, String>() {

    override fun invoke(params: Int): Flow<Resource<String>> = execute {
        songRepository.deleteSong(params)
    }
}
