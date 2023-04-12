package kr.hs.dgsw.smartschool.data.network.request.lostfound

data class AddCommentRequest(
    val comment: String,
    val lostFoundId: Int
)
