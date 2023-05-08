package kr.hs.dgsw.smartschool.domain.model.eveningstudy

import java.util.Date

data class EveningStudyItem(
    val allowCheck: EveningStudyStatus,
    val checkedAt: Date,
    val content: String,
    val createdAt: Date?,
    val endAt: Date,
    val id: Int,
    val isPhone: Boolean,
    val place: String,
    val reason: String,
    val startAt: String,
    val EveningStudyStudyStudentResponse: EveningStudyStudentItem
) {
    constructor() : this(
        EveningStudyStatus.PENDING,
        Date(),
        "",
        null,
        Date(),
        0,
        false,
        "",
        "",
        "",
        EveningStudyStudentItem(
            0,
            "",
            0,
            0
        )
    )
}
