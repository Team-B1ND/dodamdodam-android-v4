package kr.hs.dgsw.smartschool.data.network.request.studyroom

data class DefaultStudyRoomRequest(
    val day: String,
    val defaultStudyRooms: List<DefaultStudyRoomsRequest>
)
