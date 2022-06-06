package kr.hs.dgsw.smartschool.domain.usecase.sign

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.repository.SignUpRepository
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
){
    operator fun invoke(
        signUpRequest: SignUpRequest
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val result = signUpRepository.signUp(signUpRequest)
            emit(Resource.Success<String>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<String>("Couldn't reach server. Check your internet connection"))
        }
    }
}