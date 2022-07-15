package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteToken @Inject constructor(
    override val repository: TokenRepository
) : BaseUseCase<TokenRepository>() {

    suspend operator fun invoke() = repository.deleteToken()
}