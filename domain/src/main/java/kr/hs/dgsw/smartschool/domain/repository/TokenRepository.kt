package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.token.Token

interface TokenRepository {
    suspend fun getToken() : Token

    suspend fun updateNewToken() : Token

    suspend fun deleteToken()
}