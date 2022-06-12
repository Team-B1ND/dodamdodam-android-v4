package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.RetrofitRemote
import kr.hs.dgsw.smartschool.data.network.api.SignUpApi
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest

class SignUpRemote : RetrofitRemote<SignUpApi>() {
    override val api: SignUpApi
        get() = createApi(SignUpApi::class.java)

    suspend fun signUp(signUpRequest: SignUpRequest): String =
        api.signUp(signUpRequest).message
}