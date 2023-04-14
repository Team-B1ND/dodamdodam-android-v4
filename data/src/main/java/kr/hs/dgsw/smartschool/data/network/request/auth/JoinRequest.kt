package kr.hs.dgsw.smartschool.data.network.request.auth

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("grade")
    val grade: Int,
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("number")
    val number: Int,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("pw")
    val pw: String,
    @field:SerializedName("room")
    val room: Int,
)
