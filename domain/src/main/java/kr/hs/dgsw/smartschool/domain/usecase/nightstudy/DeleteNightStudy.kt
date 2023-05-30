package kr.hs.dgsw.smartschool.domain.usecase.nightstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteNightStudy @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) : UseCase<Int, String>() {

    override fun invoke(params: Int): Flow<Resource<String>> = execute {
        nightStudyRepository.deleteNightStudy(params)
    }
}
