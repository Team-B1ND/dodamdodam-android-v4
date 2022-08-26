package kr.hs.dgsw.smartschool.domain.request

data class LostFoundCommentPutRequest(
    val comment: String,
    val commentIdx: Int
)