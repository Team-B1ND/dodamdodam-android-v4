package kr.hs.dgsw.smartschool.data.network.response.point

data class PointResponse(
    val reason: String,
    val idx: Int,
    val score: Int,
    val studentIdx: Int,
    val teacherIdx: Int,
    val type: PointTypeResponse,
    val target: PointPlaceResponse
)
