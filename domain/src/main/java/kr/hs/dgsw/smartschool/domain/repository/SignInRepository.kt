package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.request.SignInRequest

interface SignInRepository {
    suspend fun signIn(signInRequest: SignInRequest)
}