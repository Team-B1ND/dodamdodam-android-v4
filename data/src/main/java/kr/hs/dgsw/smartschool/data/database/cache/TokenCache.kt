package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.TokenDao
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import javax.inject.Inject

class TokenCache @Inject constructor(application: Application) : BaseCache(application) {
    private val tokenDao: TokenDao = database.tokenDao()
    suspend fun insertToken(tokenEntity: TokenEntity) = tokenDao.insert(tokenEntity)
    suspend fun deleteToken() = tokenDao.deleteToken()
    suspend fun getToken(): TokenEntity = tokenDao.getToken()
}