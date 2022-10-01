package kr.hs.dgsw.smartschool.domain.model.lostfound

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class Comment(
    val comment: String,
    @SerializedName("id") val idx: Int,
    val lostFound: LostFound,
    val member: Member
)
