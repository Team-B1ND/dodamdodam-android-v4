package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

data class SignUpState(
    val isLoading: Boolean = false,
    val result: String = "",
    val error: String = ""
)
