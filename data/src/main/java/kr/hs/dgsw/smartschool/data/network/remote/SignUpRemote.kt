package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.network.api.SignUpApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import javax.inject.Inject

class SignUpRemote @Inject constructor(private val signUpApi: SignUpApi) {
    suspend fun signUp(signUpRequest: SignUpRequest): Response<Any> =
        signUpApi.signUp(signUpRequest)
}