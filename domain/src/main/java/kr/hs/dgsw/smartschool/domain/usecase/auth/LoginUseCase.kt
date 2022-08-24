package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.request.LoginRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val repository: AuthRepository
) : BaseUseCase<LoginUseCase.Params, Unit>() {

    override fun invoke(params: Params): Flow<Resource<Unit>> = execute {
        repository.login(LoginRequest(params.id, params.pw))
    }

    data class Params(
        val id: String,
        val pw: String
    )
}
