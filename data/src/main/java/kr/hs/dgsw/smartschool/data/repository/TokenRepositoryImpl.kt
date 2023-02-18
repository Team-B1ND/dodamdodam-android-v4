package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {

    override suspend fun getToken(): Token =
        tokenDataSource.getToken().toModel()

    override suspend fun updateNewToken(): Token =
        tokenDataSource.updateNewToken().toModel()

    override suspend fun deleteToken() =
        tokenDataSource.deleteToken()
}
