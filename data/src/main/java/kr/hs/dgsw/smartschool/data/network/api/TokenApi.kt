package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("token/refresh")
    suspend fun getNewToken(
        @Body token: String
    ): Response<String>
}
