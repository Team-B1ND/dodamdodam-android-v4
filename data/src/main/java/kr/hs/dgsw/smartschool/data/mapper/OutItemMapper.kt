package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.member.StudentIdResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherIdResponse
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import kr.hs.dgsw.smartschool.data.network.response.out.OutStatusResponse
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import java.util.Date

fun OutItemResponse.toModel(): OutItem = OutItem(
    arrivedDate = this.arrivedDate ?: Date(),
    checkedDate = this.checkedDate ?: Date(),
    endOutDate = this.endOutDate,
    id = this.id,
    reason = this.reason,
    startOutDate = this.startOutDate,
    status = this.status.toModel(),
    student = this.student.toModel(),
    teacher = this.teacher?.toModel() ?: TeacherId("")
)

fun OutStatusResponse.toModel(): OutStatus = when (this.name) {
    OutStatusResponse.ALLOWED.name -> OutStatus.ALLOWED
    OutStatusResponse.DENIED.name -> OutStatus.DENIED
    else -> OutStatus.PENDING
}

fun StudentIdResponse.toModel(): StudentId = StudentId(
    id = this.id
)

fun TeacherIdResponse.toModel(): TeacherId = TeacherId(
    id = this.id
)
