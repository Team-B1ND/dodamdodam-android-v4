package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.entity.ClassInfoEntity
import javax.inject.Inject

class ClassInfoCache @Inject constructor(application: Application) : BaseCache(application) {
    private val classInfoDao = database.classInfoDao()

    suspend fun insertClassInfoList(classInfoList: List<ClassInfoEntity>) = classInfoDao.insert(classInfoList)

    suspend fun deleteAll() = classInfoDao.deleteAll()

    suspend fun getAllClassInfo(): List<ClassInfoEntity> = classInfoDao.getAllClassInfo()

    suspend fun getClassInfo(idx: Int): ClassInfoEntity = classInfoDao.getClassInfo(idx)
}
