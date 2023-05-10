package kr.hs.dgsw.smartschool.data.network.response.nightstudy

import com.google.gson.annotations.SerializedName

data class NightStudyResponse(
    @SerializedName("allowCheck") val allowCheck: NightStudyStatusResponse,
    @SerializedName("checkedAt") val checkedAt: String?,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("endAt") val endAt: String,
    @SerializedName("id") val id: Int,
    @SerializedName("isPhone")val isPhone: Boolean,
    @SerializedName("place")val place: String,
    @SerializedName("reason")val reason: String,
    @SerializedName("startAt")val startAt: String,
    @SerializedName("NightStudyStudyStudentResponse")val NightStudyStudyStudentResponse: NightStudyStudentResponse
)
