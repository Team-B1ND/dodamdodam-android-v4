package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyResponse
import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyStatusResponse
import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyStudentResponse
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyStatus
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyStudentItem

fun EveningStudyResponse.toModel(): EveningStudyItem = EveningStudyItem(
    allowCheck = this.allowCheck.toModel(),
    checkedAt = this.checkedAt,
    content = this.content,
    createdAt = this.createdAt,
    endAt = this.endAt,
    id = this.id,
    isPhone = this.isPhone,
    place = this.place,
    reason = this.reason,
    startAt = this.startAt,
    EveningStudyStudyStudentResponse = this.EveningStudyStudyStudentResponse.toModel()
)

fun EveningStudyStudentResponse.toModel(): EveningStudyStudentItem = EveningStudyStudentItem(
    grade = this.grade,
    name = this.name,
    number = this.number,
    room = this.room
)

fun EveningStudyStatusResponse.toModel(): EveningStudyStatus = when (this.name) {
    EveningStudyStatusResponse.ALLOWED.name -> EveningStudyStatus.ALLOWED
    EveningStudyStatusResponse.DENIED.name -> EveningStudyStatus.DENIED
    else -> EveningStudyStatus.PENDING
}
