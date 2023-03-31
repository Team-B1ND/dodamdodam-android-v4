package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.datasource.AuthDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.data.mapper.toRequest
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.param.auth.JoinParam
import kr.hs.dgsw.smartschool.domain.param.auth.LoginParam
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource,
    private val accountDataSource: AccountDataSource
) : AuthRepository {

    override suspend fun join(joinParam: JoinParam): String {
        return authDataSource.join(joinParam.toRequest())
    }

    override suspend fun login(loginParam: LoginParam) {
        authDataSource.login(loginParam.toRequest()).also {
            accountDataSource.insertAccount(AccountEntity(loginParam.id, loginParam.pw))
            tokenDataSource.insertToken(Token(it.token, it.refreshToken))
        }
    }
}
