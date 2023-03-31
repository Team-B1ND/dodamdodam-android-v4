package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.auth.JoinParam
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<JoinUseCase.Params, String>() {
    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        authRepository.join(
            JoinParam(
                email = params.email,
                grade = params.grade,
                id = params.id,
                name = params.name,
                number = params.number,
                phone = params.phone,
                pw = params.pw,
                room = params.room
            )
        )
    }

    data class Params(
        val email: String,
        val grade: Int,
        val id: String,
        val name: String,
        val number: Int,
        val phone: String,
        val pw: String,
        val room: Int
    )
}
