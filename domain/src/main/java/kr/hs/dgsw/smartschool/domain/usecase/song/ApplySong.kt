package kr.hs.dgsw.smartschool.domain.usecase.song

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.request.SongRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplySong @Inject constructor(
    private val songRepository: SongRepository
) : BaseUseCase<String, String>() {

    override fun invoke(params: String): Flow<Resource<String>> = execute {
        songRepository.applySong(SongRequest(videoUrl = params))
    }
}
