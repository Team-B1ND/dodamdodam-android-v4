package kr.hs.dgsw.smartschool.domain.model.lostfound

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class LostFound(
    val comment: List<Unit> = emptyList(),
    val content: String,
    val createAt: String,
    val idx: Int,
    val member: Member,
    val place: String,
    val title: String,
    val type: String,
    val image: String?
)
