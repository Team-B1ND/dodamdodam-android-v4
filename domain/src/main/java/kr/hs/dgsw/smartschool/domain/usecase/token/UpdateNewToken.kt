package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class UpdateNewToken @Inject constructor(
    override val repository: TokenRepository
) : BaseUseCase<TokenRepository>() {
    suspend operator fun invoke(): Token = repository.updateNewToken()
}