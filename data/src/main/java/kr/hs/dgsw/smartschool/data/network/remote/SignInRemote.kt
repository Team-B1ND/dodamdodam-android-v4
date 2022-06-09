package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.hs.dgsw.smartschool.data.network.api.SignInApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.request.SignInRequest

import javax.inject.Inject

class SignInRemote @Inject constructor(
    @ApplicationContext
    private val signInApi : SignInApi
) {

    suspend fun signIn(request: SignInRequest): Response<SignIn> {
        Log.d("SignInProcess", "SignInRemote : 리모트 요청")
        return signInApi.signIn(request)
    }

}