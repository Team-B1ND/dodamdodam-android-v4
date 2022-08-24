package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join

data class JoinState(
    val isLoading: Boolean = false,
    val result: String = "",
    val error: String = ""
)
