package kr.hs.dgsw.smartschool.domain.model.lostfound

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class Comment(
    val comment: String,
    val idx: Int,
    val lostFound: LostFound,
    val member: Member
)
