package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName

data class AddBusApplyRequest(
    @SerializedName("busIdx")
    val busIdx: String
)
