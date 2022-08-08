package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class UpdateNewToken @Inject constructor(
    val repository: TokenRepository
) {
    suspend operator fun invoke(): Token = repository.updateNewToken()
}