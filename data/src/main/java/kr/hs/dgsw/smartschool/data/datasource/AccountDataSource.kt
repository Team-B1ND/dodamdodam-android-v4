package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.AccountCache
import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: Any,
    override val cache: AccountCache
) : BaseDataSource<Any, AccountCache>() {

    suspend fun getAccount(): AccountEntity = cache.getAccount()

    suspend fun insertAccount(accountEntity: AccountEntity) = cache.insertAccount(accountEntity)

    suspend fun deleteAccount() = cache.deleteAccount()
}
