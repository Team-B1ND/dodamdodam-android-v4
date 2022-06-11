package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.TokenCache
import kr.hs.dgsw.smartschool.data.network.remote.TokenRemote
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: TokenRemote,
    override val cache: TokenCache
) : BaseDataSource<TokenRemote, TokenCache>() {

}