package kr.hs.dgsw.smartschool.domain.model.nightstudy

import java.util.Date

data class NightStudyItem(
    val allowCheck: NightStudyStatus,
    val checkedAt: Date?,
    val content: String,
    val createdAt: Date?,
    val endAt: Date,
    val id: Int,
    val isPhone: Boolean,
    val place: String,
    val reason: String,
    val startAt: String,
    val NightStudyStudyStudentResponse: NightStudyStudentItem?
) {
    constructor() : this(
        NightStudyStatus.PENDING,
        Date(),
        "",
        null,
        Date(),
        0,
        false,
        "",
        "",
        "",
        NightStudyStudentItem(
            0,
            "",
            0,
            0
        )
    )
}
