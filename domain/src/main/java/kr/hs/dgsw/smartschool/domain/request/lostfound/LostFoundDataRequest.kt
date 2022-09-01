package kr.hs.dgsw.smartschool.domain.request.lostfound

data class LostFoundDataRequest(
    val content : String,
    val lostFoundId : Int?,
    val picture : String,
    val place : String,
    val title : String,
    val type : String
)