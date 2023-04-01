package kr.hs.dgsw.smartschool.data.network.request.lostfound

data class ModifyCommentRequest(
    val comment: String,
    val commentId: Int
)
