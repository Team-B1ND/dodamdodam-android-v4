package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound
import kotlinx.coroutines.flow.MutableSharedFlow
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound

data class GetCommentState(
    val isLoading: Boolean = false,
    val list: List<Comment> = emptyList(),
    val error: String = ""
)
