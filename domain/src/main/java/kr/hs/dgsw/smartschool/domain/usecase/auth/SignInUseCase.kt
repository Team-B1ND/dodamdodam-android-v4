package kr.hs.dgsw.smartschool.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    val repository: SignInRepository
) : BaseUseCase<SignInUseCase.Params, Unit>() {

    override fun invoke(params: Params): Flow<Resource<Unit>> = execute {
        repository.signIn(SignInRequest(params.id, params.pw, params.encryption))
    }

    data class Params(
        val id: String,
        val pw: String,
        val encryption: Boolean = true
    )
}
