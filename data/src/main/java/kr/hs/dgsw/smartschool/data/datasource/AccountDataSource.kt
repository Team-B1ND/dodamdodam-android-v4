package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.AccountCache
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.account.Account
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: Any,
    override val cache: AccountCache
) : BaseDataSource<Any, AccountCache> {

    suspend fun getAccount(): Account = cache.getAccount().toModel()

    suspend fun insertAccount(account: Account) = cache.insertAccount(account.toEntity())

    suspend fun deleteAccount() = cache.deleteAccount()
}
