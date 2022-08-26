package kr.hs.dgsw.smartschool.domain.request

data class LostFoundCommentPostRequest(
    val comment: String,
    val lostFoundIdx: Int
)