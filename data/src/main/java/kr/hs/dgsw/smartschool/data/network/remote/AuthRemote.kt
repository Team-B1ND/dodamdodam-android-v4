package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.RetrofitRemote
import kr.hs.dgsw.smartschool.data.network.api.AuthApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.LoginData
import kr.hs.dgsw.smartschool.domain.request.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.LoginRequest

class AuthRemote : RetrofitRemote<AuthApi>() {
    override val api: AuthApi
        get() = createApi(AuthApi::class.java)

    suspend fun join(joinRequest: JoinRequest): String {
        return api.join(joinRequest).message
    }

    suspend fun login(request: LoginRequest): Response<LoginData> =
        api.login(request)
}
