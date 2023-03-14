package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import kr.hs.dgsw.smartschool.domain.model.token.Token

fun TokenEntity?.toModel(): Token = Token(
    token = this?.token ?: "",
    refreshToken = this?.refreshToken ?: ""
)

fun Token.toEntity(): TokenEntity = TokenEntity(
    idx = 0,
    token = this.token,
    refreshToken = this.refreshToken
)
