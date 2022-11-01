package kr.hs.dgsw.smartschool.domain.model.lostfound

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class LostFound(
    val content: String,
    val createAt: String,
    @SerializedName("id") val idx: Int,
    val member: Member,
    val place: String,
    val title: String,
    val type: String,
    val image: String
)
