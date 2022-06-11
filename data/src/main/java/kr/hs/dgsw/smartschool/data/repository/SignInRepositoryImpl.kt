package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.datasource.SignInDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource,
    private val tokenDataSource: TokenDataSource,
    private val accountDataSource: AccountDataSource
): SignInRepository {
    override suspend fun signIn(signInRequest: SignInRequest) {
        signInDataSource.signIn(signInRequest).also {
            accountDataSource.insertAccount(AccountEntity(signInRequest.id!!, signInRequest.pw!!))
            tokenDataSource.insertToken(Token(it.token, it.refreshToken))
        }
    }

}