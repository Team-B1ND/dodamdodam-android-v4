package kr.hs.dgsw.smartschool.domain.model.nightstudy

data class NightStudyItem(
    val allowCheck: NightStudyStatus,
    val checkedAt: String?,
    val content: String,
    val createdAt: String,
    val endAt: String,
    val id: Int,
    val isPhone: Boolean,
    val place: String,
    val reason: String,
    val startAt: String,
    val nightStudyStudentItem: NightStudyStudentItem?
) {
    constructor() : this(
        NightStudyStatus.PENDING,
        "",
        "",
        "",
        "",
        0,
        false,
        "",
        "",
        "",
        NightStudyStudentItem()
    )
}
