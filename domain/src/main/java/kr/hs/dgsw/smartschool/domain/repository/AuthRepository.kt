package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.param.auth.JoinParam
import kr.hs.dgsw.smartschool.domain.param.auth.LoginParam

interface AuthRepository {

    suspend fun join(joinParam: JoinParam): String

    suspend fun login(loginParam: LoginParam)
}
