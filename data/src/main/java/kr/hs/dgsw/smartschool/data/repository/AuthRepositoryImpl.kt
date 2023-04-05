package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.datasource.AuthDataSource
import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource,
    private val accountDataSource: AccountDataSource
) : AuthRepository {

    override suspend fun join(email: String, grade: Int, id: String, name: String, number: Int, phone: String, pw: String, room: Int): String {
        return authDataSource.join(email, grade, id, name, number, phone, pw, room)
    }

    override suspend fun login(id: String, pw: String, encryption: Boolean) {
        authDataSource.login(id, pw, encryption).also {
            accountDataSource.insertAccount(AccountEntity(id, pw).toModel())
            tokenDataSource.insertToken(Token(it.token, it.refreshToken))
        }
    }
}
