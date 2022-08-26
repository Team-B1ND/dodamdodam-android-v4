package kr.hs.dgsw.b1nd.dodamdodam.domain.request

data class LostFoundCommentPutRequest(
    val comment: String,
    val commentIdx: Int
)