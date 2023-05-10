package kr.hs.dgsw.smartschool.domain.usecase.nightgstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyNightStudy @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) : NoParamUseCase<List<NightStudyItem>>() {

    override fun invoke(): Flow<Resource<List<NightStudyItem>>> = execute {
        nightStudyRepository.getMyNightStudy()
    }
}
