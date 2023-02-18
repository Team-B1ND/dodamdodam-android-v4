package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.domain.model.account.Account

fun AccountEntity.toModel(): Account = Account(
    id = this.id,
    pw = this.pw
)

fun Account.toEntity(): AccountEntity = AccountEntity(
    id = this.id,
    pw = this.pw
)
