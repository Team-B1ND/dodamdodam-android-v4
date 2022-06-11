package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    override val repository: SignInRepository
): BaseUseCase<SignInRepository>() {

    operator fun invoke(id : String, pw : String, encryption: Boolean = true): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            val result = repository.signIn(SignInRequest(id, pw, encryption))
            emit(Resource.Success<Unit>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<Unit>(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<Unit>("서버에 도달 할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }

}
