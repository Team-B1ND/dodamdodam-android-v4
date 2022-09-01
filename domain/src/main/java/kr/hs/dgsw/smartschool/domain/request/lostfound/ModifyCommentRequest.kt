package kr.hs.dgsw.smartschool.domain.request.lostfound

data class ModifyCommentRequest(
    val comment: String,
    val commentId : Int
)