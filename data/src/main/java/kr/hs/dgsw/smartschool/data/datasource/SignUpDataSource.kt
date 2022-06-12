package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.SignUpRemote
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    override val remote: SignUpRemote,
    override val cache: Any
): BaseDataSource<SignUpRemote, Any>() {
    suspend fun signUp(signUpRequest: SignUpRequest): String = remote.signUp(signUpRequest)
}