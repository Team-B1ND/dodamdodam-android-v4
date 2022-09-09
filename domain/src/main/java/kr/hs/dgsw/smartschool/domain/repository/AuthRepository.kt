package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.auth.LoginRequest

interface AuthRepository {

    suspend fun join(joinRequest: JoinRequest): String

    suspend fun login(loginRequest: LoginRequest)
}
