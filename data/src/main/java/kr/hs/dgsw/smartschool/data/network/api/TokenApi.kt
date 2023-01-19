package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST(DodamUrl.Token.REFRESH)
    suspend fun getNewToken(
        @Body token: String
    ): Response<String>
}
