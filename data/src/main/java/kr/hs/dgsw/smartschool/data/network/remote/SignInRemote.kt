package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.DAuthRemote
import kr.hs.dgsw.smartschool.data.network.api.SignInApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.SignInData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest

class SignInRemote : DAuthRemote<SignInApi>() {

    override val api: SignInApi
        get() = createApi(SignInApi::class.java)

    suspend fun signIn(request: SignInRequest): Response<SignInData> =
        api.signIn(request)
}
