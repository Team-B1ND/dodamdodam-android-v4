package kr.hs.dgsw.smartschool.domain.model.token

import com.google.gson.annotations.SerializedName

data class Token(
    val token: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)