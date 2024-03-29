package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.RetrofitRemote
import kr.hs.dgsw.smartschool.data.network.api.TokenApi
import kr.hs.dgsw.smartschool.data.network.response.Response

class TokenRemote : RetrofitRemote<TokenApi>() {
    override val api: TokenApi
        get() = createApi(TokenApi::class.java)

    suspend fun getNewToken(token: String): Response<String> =
        api.getNewToken(token)
}
