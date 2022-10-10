package kr.hs.dgsw.smartschool.domain.model.point

data class Log(
    val idx: Int,
    val type: Int,
    val score: Int,
    val reason: String,
    val studentIdx: Int,
    val teacherIdx: Int,
    val target: Int,
    val givenTime: String
)
