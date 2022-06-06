package kr.hs.dgsw.smartschool.domain.request

data class SignInRequest(
    var id: String,
    val pw: String
)
