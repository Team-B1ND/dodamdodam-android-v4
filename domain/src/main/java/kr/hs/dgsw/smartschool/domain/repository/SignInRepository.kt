package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.model.response.UserData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest

interface SignInRepository {
    suspend fun SignIn(signInRequest: SignInRequest) : SignIn
    suspend fun autoSignIn(): SignIn
}