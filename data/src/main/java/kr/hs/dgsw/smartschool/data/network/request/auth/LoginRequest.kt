package kr.hs.dgsw.smartschool.data.network.request.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("pw")
    val pw: String,
    @field:SerializedName("encryption")
    val encryption: Boolean,
)
