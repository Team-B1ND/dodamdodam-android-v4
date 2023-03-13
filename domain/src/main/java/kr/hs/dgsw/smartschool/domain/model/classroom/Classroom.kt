package kr.hs.dgsw.smartschool.domain.model.classroom

data class Classroom(
    val id: Int,
    val grade: Int,
    val place: ClassroomPlace,
    val room: Int
)
