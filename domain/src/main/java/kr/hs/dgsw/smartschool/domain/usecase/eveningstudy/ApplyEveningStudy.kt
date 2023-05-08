package kr.hs.dgsw.smartschool.domain.usecase.eveningstudy

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.repository.EveningStudyRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyEveningStudy @Inject constructor(
    private val eveningStudyRepository: EveningStudyRepository
) : UseCase<ApplyEveningStudy.Params, EveningStudyItem>() {

    override fun invoke(params: Params): Flow<Resource<EveningStudyItem>> = execute {
        eveningStudyRepository.applyEveningStudy(
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
