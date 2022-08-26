package kr.hs.dgsw.smartschool.domain.model.lostfound

data class LostInfo(
    val idx: Int = 0,
    val img : String = "",
    val name : String = "",
    val uploadTime : String = "",
    val title : String = "",
    val content : String = "",
    val place : String = ""
)
