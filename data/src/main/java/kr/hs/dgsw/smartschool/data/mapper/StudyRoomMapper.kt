package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomStatusResponse
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus

fun StudyRoomResponse.toModel(): StudyRoom = StudyRoom(
    date = this.date,
    id = this.id,
    place = this.place?.toModel(),
    status = this.status?.toModel(),
    student = this.student?.toModel(),
    teacher = this.teacher?.toModel(),
    timeTable = this.timeTable?.toModel()
)

fun StudyRoomResponse.StudentIdResponse.toModel(): StudyRoom.StudentId = StudyRoom.StudentId(
    id = this.id
)

fun StudyRoomResponse.TeacherIdResponse.toModel(): StudyRoom.TeacherId = StudyRoom.TeacherId(
    id = this.id
)

fun StudyRoomStatusResponse.toModel(): StudyRoomStatus = when (this.name) {
    StudyRoomStatus.ALLOWED.name -> StudyRoomStatus.ALLOWED
    StudyRoomStatus.DENIED.name -> StudyRoomStatus.DENIED
    else -> StudyRoomStatus.PENDING
}
