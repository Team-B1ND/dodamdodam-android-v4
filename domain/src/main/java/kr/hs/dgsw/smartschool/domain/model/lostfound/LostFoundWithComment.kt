package kr.hs.dgsw.smartschool.domain.model.lostfound

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.place.Place

data class LostFoundWithComment(
    val comment : String,
    val id : Int,
    val lostFound : LostFound,
    val member: Member
)
