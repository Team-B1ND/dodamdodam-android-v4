package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("auth/join")
    suspend fun signUp(
        @Body SignUpRequest: SignUpRequest
    ): Response<Any>
}