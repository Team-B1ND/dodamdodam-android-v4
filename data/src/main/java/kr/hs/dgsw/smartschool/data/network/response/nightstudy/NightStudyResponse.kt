package kr.hs.dgsw.smartschool.data.network.response.nightstudy

import java.util.Date

data class NightStudyResponse(
    val allowCheck: NightStudyStatusResponse,
    val checkedAt: Date?,
    val content: String,
    val createdAt: Date?,
    val endAt: Date,
    val id: Int,
    val isPhone: Boolean,
    val place: String,
    val reason: String,
    val startAt: String,
    val NightStudyStudyStudentResponse: NightStudyStudentResponse
)
