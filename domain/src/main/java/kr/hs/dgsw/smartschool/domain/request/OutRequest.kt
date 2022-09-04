package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName

data class OutRequest(
    @field:SerializedName("endOutDate") val endOutDate: String,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("startOutDate") val startOutDate: String
)
