package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.request.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.LoginRequest

interface AuthRepository {

    suspend fun join(joinRequest: JoinRequest): String

    suspend fun login(loginRequest: LoginRequest)
}
