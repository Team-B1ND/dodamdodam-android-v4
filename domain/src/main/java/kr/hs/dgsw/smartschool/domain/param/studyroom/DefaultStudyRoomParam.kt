package kr.hs.dgsw.smartschool.domain.param.studyroom

data class DefaultStudyRoomParam(
    val day: String,
    val defaultStudyRooms: List<DefaultStudyRoom>
) {
    data class DefaultStudyRoom(
        val placeId: Int,
        val timeTableId: Int
    )
}