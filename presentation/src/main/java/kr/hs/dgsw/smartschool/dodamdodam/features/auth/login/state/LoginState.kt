package kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.state

data class LoginState(
    val isSuccess: Boolean = false,
    val error: String = ""
)
