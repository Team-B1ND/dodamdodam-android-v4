package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class GetMyInfoState(
    val isLoading: Boolean = false,
    val myId: String = "",
    val error: String = ""
)
