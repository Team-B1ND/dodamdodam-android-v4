package kr.hs.dgsw.smartschool.domain.param.lostfound

data class AddCommentRequest(
    val comment: String,
    val lostFoundId: Int
)
