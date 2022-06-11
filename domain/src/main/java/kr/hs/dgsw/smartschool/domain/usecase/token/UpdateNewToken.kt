package kr.hs.dgsw.smartschool.domain.usecase.token

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateNewToken @Inject constructor(
    override val repository: TokenRepository
) : BaseUseCase<TokenRepository>() {

    operator fun invoke(): Flow<Resource<Token>> = flow {
        try {
            emit(Resource.Loading<Token>())
            val token = repository.updateNewToken()
            emit(Resource.Success<Token>(token))
        } catch (e: HttpException) {
            emit(Resource.Error<Token>(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<Token>("서버에 도달 할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }

}