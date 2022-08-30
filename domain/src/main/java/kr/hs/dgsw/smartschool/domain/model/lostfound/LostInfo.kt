package kr.hs.dgsw.smartschool.domain.model.lostfound

data class LostInfo(
    val img : String = "",
    val name : String = "",
    val uploadTime : String = "",
    val title : String = "",
    val content : String = "",
    val place : String = ""
)
