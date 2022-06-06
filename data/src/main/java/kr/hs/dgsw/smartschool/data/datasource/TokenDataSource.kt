package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.database.cache.TokenCache
import kr.hs.dgsw.smartschool.data.network.remote.SignInRemote
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val remote: SignInRemote,
    private val tokenCache: TokenCache
) {
    suspend fun getToken():String{
        return tokenCache.getToken()
    }
    suspend fun getRefreshToken():String{
        return tokenCache.getRefreshToken()
    }
    suspend fun updateToken(token:String){
        tokenCache.updateToken(token)
    }

    suspend fun updateRefreshToken(refreshToken:String){
        tokenCache.updateRefreshToken(refreshToken)
    }

}