package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.AccountDao
import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import javax.inject.Inject

class AccountCache @Inject constructor(application: Application) : BaseCache(application) {
    private val accountDao: AccountDao = database.accountDao()

    suspend fun getAccount(): AccountEntity = accountDao.getAccount()

    suspend fun insertAccount(accountEntity: AccountEntity) = accountDao.insert(accountEntity)

    suspend fun deleteAccount() = database.accountDao().deleteAccount()
}
