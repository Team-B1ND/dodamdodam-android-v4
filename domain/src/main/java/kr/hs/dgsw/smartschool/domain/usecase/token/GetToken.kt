package kr.hs.dgsw.smartschool.domain.usecase.token

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetToken @Inject constructor(
    override val repository: TokenRepository
) : BaseUseCase<TokenRepository>() {
    suspend operator fun invoke(): Token = repository.getToken()
}