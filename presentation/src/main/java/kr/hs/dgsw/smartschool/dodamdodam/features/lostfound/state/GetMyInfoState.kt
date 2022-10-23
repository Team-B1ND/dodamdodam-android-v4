package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state

data class GetMyInfoState(
    val isLoading: Boolean = false,
    val myId: String = "",
    val error: String = ""
)
