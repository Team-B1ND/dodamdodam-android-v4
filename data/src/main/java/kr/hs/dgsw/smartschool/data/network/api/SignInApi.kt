package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import retrofit2.http.*

interface SignInApi {
    @POST("auth/login")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignIn>
}