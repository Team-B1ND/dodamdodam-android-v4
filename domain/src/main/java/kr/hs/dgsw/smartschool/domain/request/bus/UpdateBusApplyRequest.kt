package kr.hs.dgsw.smartschool.domain.request.bus

import com.google.gson.annotations.SerializedName

data class UpdateBusApplyRequest(
    @SerializedName("busIdx")
    val busIdx: String,
    @SerializedName("originBusIdx")
    val originBusIdx: String
)
