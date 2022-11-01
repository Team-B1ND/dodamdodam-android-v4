package kr.hs.dgsw.smartschool.domain.model.point

data class Point(
    val reason: String,
    val idx: Int,
    val score: Int,
    val studentIdx: Int,
    val teacherIdx: Int,
    val type: PointType,
    val target: PointPlace
)
