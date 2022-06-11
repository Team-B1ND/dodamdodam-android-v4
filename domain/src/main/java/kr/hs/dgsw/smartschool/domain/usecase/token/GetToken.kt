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

    operator fun invoke(): Flow<Resource<Token>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getToken()
            emit(Resource.Success<Token>(result))
        } catch (e: Exception) {
            emit(Resource.Error<Token>("오류가 발생하였습니다."))
        }

    }
}