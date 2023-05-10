package kr.hs.dgsw.smartschool.data.network.request.nightstudy

import com.google.gson.annotations.SerializedName

data class NightStudyRequest(
    @SerializedName("content")val content: String,
    @SerializedName("endAt")val endAt: String,
    @SerializedName("isPhone")val isPhone: Boolean,
    @SerializedName("placeId")val placeId: Int,
    @SerializedName("reason")val reason: String,
    @SerializedName("startAt")val startAt: String
)
