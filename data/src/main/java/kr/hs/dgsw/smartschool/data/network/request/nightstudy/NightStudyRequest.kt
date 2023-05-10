package kr.hs.dgsw.smartschool.data.network.request.nightstudy

data class NightStudyRequest(
    val content: String,
    val endAt: String,
    val isPhone: Boolean,
    val placeId: Int,
    val reason: String,
    val startAt: String
)
