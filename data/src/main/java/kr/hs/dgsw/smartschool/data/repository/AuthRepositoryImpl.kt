package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.datasource.AuthDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.domain.request.auth.LoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource,
    private val accountDataSource: AccountDataSource
) : AuthRepository {

    override suspend fun join(joinRequest: JoinRequest): String {
        return authDataSource.join(joinRequest)
    }

    override suspend fun login(loginRequest: LoginRequest) {
        authDataSource.login(loginRequest).also {
            accountDataSource.insertAccount(AccountEntity(loginRequest.id!!, loginRequest.pw!!))
            tokenDataSource.insertToken(Token(it.token, it.refreshToken))
        }
    }
}
