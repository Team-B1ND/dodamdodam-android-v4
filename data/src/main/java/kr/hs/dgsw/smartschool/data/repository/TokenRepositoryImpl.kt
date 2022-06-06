package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) :TokenRepository{
    override suspend fun getToken(): String {
        return tokenDataSource.
    }

    override suspend fun getRefreshToken(): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRefreshToken(refreshToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insertToken(token: String, refreshToken: String) {
        TODO("Not yet implemented")
    }
}