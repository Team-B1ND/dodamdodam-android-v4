package kr.hs.dgsw.smartschool.domain.model.lostfound

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class LostFound(
    val content : String,
    val createAt : String,
    val id : Int,
    val member : Member,
    val place: String,
    val title : String,
    val type : String

)
