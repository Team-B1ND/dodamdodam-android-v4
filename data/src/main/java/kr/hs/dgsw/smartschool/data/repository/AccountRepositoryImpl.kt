package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.mapper.AccountMapper
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.account.Account
import kr.hs.dgsw.smartschool.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDataSource: AccountDataSource
) : AccountRepository {


    override suspend fun getAccount(): Account {
        return accountDataSource.getAccount().toModel()
    }

    override suspend fun insertAccount(account: Account) {
        accountDataSource.insertAccount(account.toEntity())
    }

    override suspend fun deleteAccount() {
        accountDataSource.deleteAccount()
    }
}
