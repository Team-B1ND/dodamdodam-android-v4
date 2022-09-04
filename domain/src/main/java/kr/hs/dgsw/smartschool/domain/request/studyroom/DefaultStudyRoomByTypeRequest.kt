package kr.hs.dgsw.smartschool.domain.request.studyroom

data class DefaultStudyRoomByTypeRequest(
    val placeId: Int,
    val timeTableId: Int,
    val type: Int
)
