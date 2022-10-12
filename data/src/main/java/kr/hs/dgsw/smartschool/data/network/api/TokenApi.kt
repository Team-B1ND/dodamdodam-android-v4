package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.request.token.TokenRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("token")
    suspend fun getNewToken(
        @Body tokenRequest: TokenRequest
    ): Response<String>
}
