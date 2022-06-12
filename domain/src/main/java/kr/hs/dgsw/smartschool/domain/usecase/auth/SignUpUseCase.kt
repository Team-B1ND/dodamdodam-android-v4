package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.model.member.StudentInfo
import kr.hs.dgsw.smartschool.domain.repository.SignUpRepository
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    operator fun invoke(params: Params): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val result = signUpRepository.signUp(
                SignUpRequest(
                    params.id,
                    params.pw,
                    params.email,
                    params.phone,
                    params.name,
                    StudentInfo(
                        params.grade.toInt(),
                        params.classroom.toInt(),
                        params.number.toInt()
                    )
                )
            )
            emit(Resource.Success<String>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<String>("Couldn't reach server. Check your internet connection"))
        }
    }

    data class Params(
        val id: String,
        val pw: String,
        val email: String,
        val phone: String,
        val name: String,
        val grade: String,
        val classroom: String,
        val number: String
    )
}