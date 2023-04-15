package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.member.StudentIdResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherIdResponse
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import kr.hs.dgsw.smartschool.data.network.response.out.OutStatusResponse
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus

fun OutItemResponse.toModel(): OutItem = OutItem(
    arrivedDate = this.arrivedDate,
    checkedDate = this.checkedDate,
    endOutDate = this.endOutDate,
    id = this.id,
    reason = this.reason,
    startOutDate = this.startOutDate,
    status = this.status.toModel(),
    student = this.student.toModel(),
    teacher = this.teacher.toModel()
)

fun OutStatusResponse.toModel(): OutStatus = when (this.name) {
    OutStatus.ALLOWED.name -> OutStatus.ALLOWED
    OutStatus.DENIED.name -> OutStatus.DENIED
    else -> OutStatus.PENDING
}

fun StudentIdResponse.toModel(): StudentId = StudentId(
    id = this.id
)

fun TeacherIdResponse.toModel(): TeacherId = TeacherId(
    id = this.id
)
