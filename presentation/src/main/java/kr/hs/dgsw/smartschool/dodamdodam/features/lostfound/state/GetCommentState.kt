package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment

data class GetCommentState(
    val isLoading: Boolean = false,
    val list: List<Comment> = emptyList(),
    val error: String = ""
)
