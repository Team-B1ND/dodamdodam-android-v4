package kr.hs.dgsw.smartschool.domain.request.studyroom

data class DefaultStudyRoomRequest(
    val day: String,
    val defaultStudyRooms: List<DefaultStudyRoom>
) {
    data class DefaultStudyRoom(
        val placeId: Int,
        val timeTableId: Int
    )
}
