package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.request.JoinRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<JoinUseCase.Params, String>() {
    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        authRepository.join(
            JoinRequest(
                params.email,
                params.grade,
                params.id,
                params.name,
                params.number,
                params.phone,
                params.pw,
                params.room
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
