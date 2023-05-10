package kr.hs.dgsw.smartschool.data.network.request.out

import com.google.gson.annotations.SerializedName

data class OutRequest(
    @SerializedName("endOutDate")val endOutDate: String,
    @SerializedName("reason")val reason: String,
    @SerializedName("startOutDate")val startOutDate: String
)
