package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.SignInDao
import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.domain.model.response.UserData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest

import javax.inject.Inject

class SignInCache @Inject constructor(application: Application): BaseCache(application) {
    private val signInDao: SignInDao = database.signInDao()

    suspend fun insertData(token: String,id:String, pw:String){
        signInDao.insertData(token,id,pw)
    }
    suspend fun getData(token:String) : SignInRequest{
        Log.e("SignInCache","로그인 리퀘스트 추출 성공 : ${signInDao.getData(token)}")
        return signInDao.getData(token)
    }
    suspend fun deleteAll(){
        signInDao.deleteAll()
    }
}