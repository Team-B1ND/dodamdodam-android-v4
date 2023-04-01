package kr.hs.dgsw.smartschool.domain.param.lostfound

data class LostFoundDataParam(
    val content: String,
    val lostFoundId: Int?,
    val picture: String,
    val place: String,
    val title: String,
    val type: String
)
