package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem

interface EveningStudyRepository {
    suspend fun applyEveningStudy(content: String, endAt: String, isPhone: Boolean, placeId: Int, reason: String, startAt: String): EveningStudyItem
//    suspend fun modifyEveningStudy(startEveningStudyDate: String, endEveningStudyDate: String, reason: String, eveningStudyId: Int, isNeedPhone: Boolean, phoneReason: String): EveningStudyItem
    suspend fun deleteEveningStudy(eveningStudyId: Int): String
    suspend fun getMyEveningStudy(): List<EveningStudyItem>
}
