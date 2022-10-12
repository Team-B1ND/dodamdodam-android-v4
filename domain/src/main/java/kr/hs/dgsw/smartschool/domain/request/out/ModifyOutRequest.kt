package kr.hs.dgsw.smartschool.domain.request.out

import com.google.gson.annotations.SerializedName

data class ModifyOutRequest(
    @field:SerializedName("endOutDate") val endOutDate: String,
    @field:SerializedName("outId") val outId: Int,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("startOutDate") val startOutDate: String
)
