package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.AuthRemote
import kr.hs.dgsw.smartschool.data.network.response.data.LoginData
import kr.hs.dgsw.smartschool.domain.request.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.LoginRequest
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: Any
) : BaseDataSource<AuthRemote, Any> {

    suspend fun join(joinRequest: JoinRequest): String {
        return remote.join(joinRequest)
    }

    suspend fun login(loginRequest: LoginRequest): LoginData {
        return remote.login(loginRequest).data
    }
}
