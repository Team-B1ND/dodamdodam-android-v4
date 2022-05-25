package kr.hs.dgsw.smartschool.data.network.response.data



data class SignIn(
    var userData: UserData,
    val token: String,
    var refreshToken: String
)