package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.LoginData
import kr.hs.dgsw.smartschool.domain.request.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/join")
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): Response<Any>

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginData>
}
