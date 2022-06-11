package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TokenDataSource
import kr.hs.dgsw.smartschool.data.mapper.TokenMapper
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {

    private val tokenMapper = TokenMapper()

    override suspend fun getToken(): Token =
        tokenDataSource.getToken().let { tokenMapper.mapToModel(it) }


    override suspend fun updateNewToken(): Token =
        tokenDataSource.updateNewToken().let { tokenMapper.mapToModel(it) }

    override suspend fun deleteToken() =
        tokenDataSource.deleteToken()


}