package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class GetToken @Inject constructor(
    val repository: TokenRepository
) {
    suspend operator fun invoke(): Token = repository.getToken()
}
