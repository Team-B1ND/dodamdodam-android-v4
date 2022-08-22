package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("auth/register")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<Any>
}
