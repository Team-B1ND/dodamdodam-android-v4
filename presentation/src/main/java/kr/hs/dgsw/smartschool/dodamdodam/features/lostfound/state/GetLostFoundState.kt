package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound

data class GetLostFoundState(
    val isLoading: Boolean = false,
    val list: List<LostFound> = emptyList(),
    val error: String = ""
)
