package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.AccountCache
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: Any,
    override val cache: AccountCache
) : BaseDataSource<Any, AccountCache>() {

    suspend fun getAccount()

}