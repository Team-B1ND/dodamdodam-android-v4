package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import kr.hs.dgsw.smartschool.domain.model.token.Token

class TokenMapper : BaseEntityMapper<Token, TokenEntity> {

    override fun mapToModel(entity: TokenEntity): Token {
        return Token(
            entity.token,
            entity.refreshToken
        )
    }

    override fun mapToEntity(model: Token): TokenEntity {
        return TokenEntity(
            0,
            model.token,
            model.refreshToken
        )
    }
}
