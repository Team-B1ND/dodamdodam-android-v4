package kr.hs.dgsw.smartschool.domain.param.studyroom

data class DefaultStudyRoomByTypeRequest(
    val placeId: Int,
    val timeTableId: Int,
    val type: Int
)
