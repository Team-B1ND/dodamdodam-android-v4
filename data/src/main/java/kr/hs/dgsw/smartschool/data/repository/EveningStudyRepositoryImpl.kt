package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.EveningStudyDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.EveningStudyRequest
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.repository.EveningStudyRepository
import javax.inject.Inject

class EveningStudyRepositoryImpl @Inject constructor(
    private val eveningStudyDataSource: EveningStudyDataSource
) : EveningStudyRepository {
    override suspend fun applyEveningStudy(context: String, endAt: String, isPhone: Boolean, placeId: Int, reason: String, startAt: String): EveningStudyItem {
        return eveningStudyDataSource.applyEveningStudy(EveningStudyRequest(context, endAt, isPhone, placeId, reason, startAt)).toModel()
    }

    override suspend fun deleteEveningStudy(eveningStudyId: Int): String {
        return eveningStudyDataSource.deleteEveningStudy(eveningStudyId)
    }

    override suspend fun getMyEveningStudy(): List<EveningStudyItem> {
        return eveningStudyDataSource.getMyEveningStudy().map { it.toModel() }
    }
}
