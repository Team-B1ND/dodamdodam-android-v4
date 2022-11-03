package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.state

data class JoinState(
    val isLoading: Boolean = false,
    val result: String = "",
    val error: String = ""
)
