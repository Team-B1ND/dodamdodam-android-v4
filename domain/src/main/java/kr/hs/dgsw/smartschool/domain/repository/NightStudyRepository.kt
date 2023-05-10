package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem

interface NightStudyRepository {
    suspend fun applyNightStudy(content: String, endAt: String, isPhone: Boolean, placeId: Int, reason: String, startAt: String): NightStudyItem
    suspend fun deleteNightStudy(nightStudyId: Int): String
    suspend fun getMyNightStudy(): List<NightStudyItem>
}
