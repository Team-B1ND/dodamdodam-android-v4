package kr.hs.dgsw.smartschool.domain.usecase.token

data class TokenUseCases(
    val deleteToken: DeleteToken,
    val getToken: GetToken,
    val updateNewToken: UpdateNewToken,
)
