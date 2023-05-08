package kr.hs.dgsw.smartschool.domain.usecase.eveningstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.repository.EveningStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyEveningStudy @Inject constructor(
    private val eveningStudyRepository: EveningStudyRepository
) : NoParamUseCase<List<EveningStudyItem>>() {

    override fun invoke(): Flow<Resource<List<EveningStudyItem>>> = execute {
        eveningStudyRepository.getMyEveningStudy()
    }
}