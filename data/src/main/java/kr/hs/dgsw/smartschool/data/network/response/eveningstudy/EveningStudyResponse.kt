package kr.hs.dgsw.smartschool.data.network.response.eveningstudy

import java.util.Date

data class EveningStudyResponse(
    val allowCheck: EveningStudyStatusResponse,
    val checkedAt: Date,
    val content: String,
    val createdAt: Date?,
    val endAt: Date,
    val id: Int,
    val isPhone: Boolean,
    val place: String,
    val reason: String,
    val startAt: String,
    val EveningStudyStudyStudentResponse: EveningStudyStudentResponse
)
