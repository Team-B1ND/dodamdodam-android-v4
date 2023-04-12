package kr.hs.dgsw.smartschool.data.network.response.lostpound

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class CommentResponse(
    val comment: String,
    @SerializedName("id") val idx: Int,
    val lostFound: LostFound,
    val member: Member
)
