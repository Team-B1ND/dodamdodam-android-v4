package kr.hs.dgsw.smartschool.domain.model.studyroom

data class StudyRooms(
    val studentIdx: Int,
    val classIdx: Int,
    val locations: ArrayList<StudyRoom>
)
