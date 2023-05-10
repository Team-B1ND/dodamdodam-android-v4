package kr.hs.dgsw.smartschool.domain.model.nightstudy

data class NightStudyStudentItem(
    val grade: Int,
    val name: String,
    val number: Int,
    val room: Int
) {
    constructor() : this(
        0,
        "",
        0,
        0
    )
}
