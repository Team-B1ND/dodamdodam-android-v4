package kr.hs.dgsw.smartschool.domain.request.lostfound


data class GetLostFoundRequest(
    val limit : Int,
    val page : Int,
    val type : String
)