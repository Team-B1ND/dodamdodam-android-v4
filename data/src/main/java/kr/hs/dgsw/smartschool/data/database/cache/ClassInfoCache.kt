package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import javax.inject.Inject

class ClassInfoCache @Inject constructor(application: Application) : BaseCache(application) {
    private val classInfoDao = database.classInfoDao()

    suspend fun insertClassInfoList(classInfoList: List<ClassroomEntity>) = classInfoDao.insert(classInfoList)

    suspend fun deleteAll() = classInfoDao.deleteAll()

    suspend fun getAllClassInfo(): List<ClassroomEntity> = classInfoDao.getAllClassInfo()

    suspend fun getClassInfo(idx: Int): ClassroomEntity = classInfoDao.getClassInfo(idx)
}
