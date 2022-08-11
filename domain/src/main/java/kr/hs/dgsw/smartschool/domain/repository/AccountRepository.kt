package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.account.Account

interface AccountRepository {

    suspend fun getAccount() : Account

    suspend fun insertAccount(account: Account)

    suspend fun deleteAccount()
}