package kr.hs.dgsw.smartschool.data.network.request.out

import com.google.gson.annotations.SerializedName

data class ModifyOutRequest(
    @SerializedName("endOutDate")val endOutDate: String,
    @SerializedName("outId")val outId: Int,
    @SerializedName("reason")val reason: String,
    @SerializedName("startOutDate")val startOutDate: String
)
