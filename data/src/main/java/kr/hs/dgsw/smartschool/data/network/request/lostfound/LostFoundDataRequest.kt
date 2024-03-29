package kr.hs.dgsw.smartschool.data.network.request.lostfound

import com.google.gson.annotations.SerializedName

data class LostFoundDataRequest(
    val content: String,
    val lostFoundId: Int?,
    @SerializedName("image") val picture: String,
    val place: String,
    val title: String,
    val type: String
)
