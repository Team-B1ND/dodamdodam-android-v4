package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteToken @Inject constructor(
    val repository: TokenRepository
) {
    suspend operator fun invoke() = repository.deleteToken()
}