package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.NightStudyDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.NightStudyRequest
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import javax.inject.Inject

class NightStudyRepositoryImpl @Inject constructor(
    private val nightStudyDataSource: NightStudyDataSource
) : NightStudyRepository {
    override suspend fun applyNightStudy(context: String, endAt: String, isPhone: Boolean, placeId: Int, reason: String, startAt: String): NightStudyItem {
        return nightStudyDataSource.applyNightStudy(NightStudyRequest(context, endAt, isPhone, placeId, reason, startAt)).toModel()
    }

    override suspend fun deleteNightStudy(nightStudyId: Int): String {
        return nightStudyDataSource.deleteNightStudy(nightStudyId)
    }

    override suspend fun getMyNightStudy(): List<NightStudyItem> {
        return nightStudyDataSource.getMyNightStudy().map { it.toModel() }
    }
}
