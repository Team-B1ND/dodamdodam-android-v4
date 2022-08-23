package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.member.StudentInfo
import kr.hs.dgsw.smartschool.domain.repository.SignUpRepository
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseUseCase<SignUpUseCase.Params, String>() {
    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        signUpRepository.signUp(
            SignUpRequest(
                params.id, params.pw, params.email,
                params.phone,
                params.name,
                StudentInfo(
                    params.grade.toInt(),
                    params.classroom.toInt(),
                    params.number.toInt()
                )
            )
        )
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
