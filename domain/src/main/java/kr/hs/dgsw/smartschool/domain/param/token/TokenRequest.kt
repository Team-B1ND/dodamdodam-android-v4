package kr.hs.dgsw.smartschool.domain.param.token

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("refreshToken")
    val refreshToken: String
)
