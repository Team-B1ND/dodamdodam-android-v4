package kr.hs.dgsw.smartschool.data.network.response.lostpound

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.data.network.response.member.MemberResponse

data class LostFoundResponse(
    val comment: List<Unit> = emptyList(),
    val content: String,
    @SerializedName("createAt")val createAt: String,
    @SerializedName("id") val idx: Int,
    val member: MemberResponse,
    val place: String,
    val title: String,
    val type: String,
    val image: String?
)
