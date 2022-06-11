package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.TokenCache
import kr.hs.dgsw.smartschool.data.mapper.TokenMapper
import kr.hs.dgsw.smartschool.data.network.remote.TokenRemote
import kr.hs.dgsw.smartschool.domain.model.token.Token
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: TokenRemote,
    override val cache: TokenCache
) : BaseDataSource<TokenRemote, TokenCache>() {

    private val tokenMapper = TokenMapper()

    private val PAYLOAD_MEMBER_ID = "memberId"

    suspend fun insertToken(token: Token) = cache.insertToken(tokenMapper.mapToEntity(token))

    

}