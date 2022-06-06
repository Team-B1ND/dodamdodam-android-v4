package kr.hs.dgsw.smartschool.domain.model.response



data class SignIn(
    var userData: UserData,
    val token: String,
    var refreshToken: String
)