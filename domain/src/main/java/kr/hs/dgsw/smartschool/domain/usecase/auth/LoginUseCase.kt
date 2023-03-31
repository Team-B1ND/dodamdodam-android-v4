package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.auth.LoginParam
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val repository: AuthRepository
) : UseCase<LoginUseCase.Params, Unit>() {

    override fun invoke(params: Params): Flow<Resource<Unit>> = execute {
        repository.login(LoginParam(params.id, params.pw, params.encryption))
    }

    data class Params(
        val id: String,
        val pw: String,
        val encryption: Boolean = true
    )
}
