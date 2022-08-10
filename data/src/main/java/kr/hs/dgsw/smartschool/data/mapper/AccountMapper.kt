package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.domain.model.account.Account

class AccountMapper : BaseEntityMapper<Account, AccountEntity> {
    override fun mapToModel(entity: AccountEntity): Account {
        return Account(entity.id, entity.pw)
    }

    override fun mapToEntity(model: Account): AccountEntity {
        return AccountEntity(model.id, model.pw)
    }
}