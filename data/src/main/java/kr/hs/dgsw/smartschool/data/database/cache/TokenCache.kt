package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.SignInDao
import kr.hs.dgsw.smartschool.data.database.dao.TokenDao

import javax.inject.Inject

class TokenCache @Inject constructor(application: Application): BaseCache(application) {
    private val tokenDao: TokenDao = database.tokenDao()

    suspend fun insertSignInData(index:Int,token:String, refreshToken : String) = tokenDao.insertToken(index,token,refreshToken)

    suspend fun deleteAll() = tokenDao.deleteAll()

    suspend fun getToken() = tokenDao.getToken()

    suspend fun getRefreshToken() = tokenDao.getRefreshToken()

    suspend fun updateToken(token: String) = tokenDao.updateToken(token)

    suspend fun updateRefreshToken(refreshToken: String) = tokenDao.updateToken(refreshToken)

}