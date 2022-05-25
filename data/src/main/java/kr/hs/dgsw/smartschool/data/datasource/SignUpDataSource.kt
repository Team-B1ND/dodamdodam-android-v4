package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.network.remote.SignUpRemote
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    val remote: SignUpRemote
) {
    suspend fun signUp(signUpRequest: SignUpRequest): Response<Any> =
        remote.signUp(signUpRequest)
}