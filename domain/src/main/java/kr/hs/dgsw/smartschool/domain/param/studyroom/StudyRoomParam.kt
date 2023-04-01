package kr.hs.dgsw.smartschool.domain.param.studyroom

data class StudyRoomParam(
    val studyRoomList: List<RequestStudyRoom>
) {
    data class RequestStudyRoom(
        val placeId: Int,
        val timeTableId: Int
    )
}
