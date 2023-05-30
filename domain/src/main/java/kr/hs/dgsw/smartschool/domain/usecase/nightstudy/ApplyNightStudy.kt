package kr.hs.dgsw.smartschool.domain.usecase.nightstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyNightStudy @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) : UseCase<ApplyNightStudy.Params, NightStudyItem>() {

    override fun invoke(params: Params): Flow<Resource<NightStudyItem>> = execute {
        nightStudyRepository.applyNightStudy(
            params.content,
            params.endAt,
            params.isPhone,
            params.placeId,
            params.reason,
            params.startAt
        )
    }

    data class Params(
        val content: String,
        val endAt: String,
        val isPhone: Boolean,
        val placeId: Int,
        val reason: String,
        val startAt: String
    )
}
