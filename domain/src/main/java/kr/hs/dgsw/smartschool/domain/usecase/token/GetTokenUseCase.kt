package kr.hs.dgsw.smartschool.domain.usecase.token

import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.model.response.Meal
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(
    )
    :Flow<Resource<String>> = flow{
        try {
            emit(Resource.Loading())
            val result = tokenRepository.getToken()
            emit(Resource.Success(result))
            Log.e("GetTokenUseCase","${Resource.Success(result)}")



        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}