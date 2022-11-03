package kr.hs.dgsw.smartschool.domain.usecase.account

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.account.Account
import kr.hs.dgsw.smartschool.domain.repository.AccountRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseUseCase<Unit, Account>() {
    override fun invoke(params: Unit): Flow<Resource<Account>> = execute {
        accountRepository.getAccount()
    }
}
