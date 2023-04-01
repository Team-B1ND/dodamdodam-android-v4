package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.DefaultStudyRoomsRequest
import kr.hs.dgsw.smartschool.data.network.request.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomStatusResponse
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomParam

fun StudyRoomResponse.toModel(): StudyRoom = StudyRoom(
    date = this.date,
    id = this.id,
    place = place!!.toModel(),
    status = status!!.toModel(),
    student = this.student!!.toModel(),
    teacher = this.teacher!!.toModel(),
    timeTable = timeTable!!.toModel()
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

fun StudyRoomParam.toRequest(): StudyRoomRequest = StudyRoomRequest(
    studyRoomList = this.studyRoomList.map { it.toRequest() }
)

fun StudyRoomParam.RequestStudyRoom.toRequest(): StudyRoomRequest.RequestStudyRoom = StudyRoomRequest.RequestStudyRoom(
    placeId = this.placeId,
    timeTableId = this.timeTableId
)

fun DefaultStudyRoomByTypeParam.toRequest(): DefaultStudyRoomByTypeRequest = DefaultStudyRoomByTypeRequest(
    placeId = this.placeId,
    timeTableId = this.timeTableId,
    type = this.type
)

fun DefaultStudyRoomParam.toRequest(): DefaultStudyRoomRequest = DefaultStudyRoomRequest(
    day = this.day,
    defaultStudyRooms = this.defaultStudyRooms.map { it.toModel() }
)

fun DefaultStudyRoomParam.DefaultStudyRoom.toModel(): DefaultStudyRoomsRequest = DefaultStudyRoomsRequest(
    placeId = this.placeId,
    timeTableId = this.timeTableId
)
