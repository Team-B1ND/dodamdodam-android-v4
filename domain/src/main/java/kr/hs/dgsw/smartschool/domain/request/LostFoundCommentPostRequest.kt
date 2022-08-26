package kr.hs.dgsw.b1nd.dodamdodam.domain.request

data class LostFoundCommentPostRequest(
    val comment: String,
    val lostfoundIdx: Int
)