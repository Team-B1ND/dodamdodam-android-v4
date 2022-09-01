package kr.hs.dgsw.smartschool.domain.request.lostfound

data class AddCommentRequest(
    val comment: String,
    val lostFoundId : Int
)