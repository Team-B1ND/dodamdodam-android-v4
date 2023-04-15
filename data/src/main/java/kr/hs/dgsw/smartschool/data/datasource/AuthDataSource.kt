package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.AuthRemote
import kr.hs.dgsw.smartschool.data.network.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.data.network.request.auth.LoginRequest
import kr.hs.dgsw.smartschool.data.network.response.data.LoginData
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: Any
) : BaseDataSource<AuthRemote, Any> {

    suspend fun join(email: String, grade: Int, id: String, name: String, number: Int, phone: String, pw: String, room: Int): String {
        return remote.join(JoinRequest(email, grade, id, name, number, phone, pw, room))
    }

    suspend fun login(id: String, pw: String, encryption: Boolean): LoginData {
        return remote.login(LoginRequest(id, pw, encryption)).data
    }
}
