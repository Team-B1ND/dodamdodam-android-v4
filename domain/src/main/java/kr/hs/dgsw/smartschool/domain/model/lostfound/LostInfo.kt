package kr.hs.dgsw.smartschool.domain.model.lostfound

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class LostInfo(
    val idx: Int,
    val img: String = "",
    val name: String = "",
    val uploadTime: String = "",
    val title: String = "",
    val content: String = "",
    val place: String = "",
    val member: Member,
    val myId: String
)
