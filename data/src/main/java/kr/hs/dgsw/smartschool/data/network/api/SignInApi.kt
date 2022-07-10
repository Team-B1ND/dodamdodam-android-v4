package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.SignInData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import retrofit2.http.*

interface SignInApi {
    @POST("auth/login")
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<SignInData>
}