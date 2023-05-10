package kr.hs.dgsw.smartschool.data.network.response.nightstudy

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NightStudyResponse(
    @SerializedName("allowCheck") val allowCheck: NightStudyStatusResponse,
    @SerializedName("checkedAt") val checkedAt: Date?,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: Date?,
    @SerializedName("endAt") val endAt: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("isPhone")val isPhone: Boolean,
    @SerializedName("place")val place: String,
    @SerializedName("reason")val reason: String,
    @SerializedName("startAt")val startAt: String,
    @SerializedName("NightStudyStudyStudentResponse")val NightStudyStudyStudentResponse: NightStudyStudentResponse
)
