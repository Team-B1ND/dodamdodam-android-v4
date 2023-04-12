package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.data.network.request.auth.LoginRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.LoginData
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(DodamUrl.Auth.JOIN)
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): Response<Any>

    @POST(DodamUrl.Auth.LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginData>
}
