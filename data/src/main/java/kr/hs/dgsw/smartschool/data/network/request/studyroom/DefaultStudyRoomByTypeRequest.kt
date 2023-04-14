package kr.hs.dgsw.smartschool.data.network.request.studyroom

data class DefaultStudyRoomByTypeRequest(
    val placeId: Int,
    val timeTableId: Int,
    val type: Int
)
