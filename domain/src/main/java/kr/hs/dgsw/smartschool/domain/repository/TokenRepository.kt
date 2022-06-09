package kr.hs.dgsw.smartschool.domain.repository

interface TokenRepository {
    //token 관련 함수
    suspend fun getToken():String
    suspend fun getRefreshToken():String
    suspend fun updateToken(token: String)
    suspend fun updateRefreshToken(refreshToken: String)
    suspend fun insertToken(token:String,refreshToken:String)
}