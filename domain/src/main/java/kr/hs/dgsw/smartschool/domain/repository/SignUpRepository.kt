package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.request.SignUpRequest

interface SignUpRepository {
    suspend fun signUp(singUpRequest: SignUpRequest): String
}