package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyResponse
import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyStatusResponse
import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyStudentResponse
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyStatus
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyStudentItem

fun NightStudyResponse.toModel(): NightStudyItem = NightStudyItem(
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
    NightStudyStudyStudentResponse = this.NightStudyStudyStudentResponse?.toModel()
)

fun NightStudyStudentResponse.toModel(): NightStudyStudentItem = NightStudyStudentItem(
    grade = this.grade,
    name = this.name,
    number = this.number,
    room = this.room
)

fun NightStudyStatusResponse.toModel(): NightStudyStatus = when (this.name) {
    NightStudyStatusResponse.ALLOWED.name -> NightStudyStatus.ALLOWED
    NightStudyStatusResponse.DENIED.name -> NightStudyStatus.DENIED
    else -> NightStudyStatus.PENDING
}
