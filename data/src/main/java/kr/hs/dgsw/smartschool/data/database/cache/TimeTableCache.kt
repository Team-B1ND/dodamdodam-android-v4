package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.TimeDao
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import javax.inject.Inject

class TimeTableCache @Inject constructor(application: Application) : BaseCache(application) {

    private val timeDao: TimeDao = database.timeDao()

    suspend fun insertTime(timeEntityList: List<TimeEntity>) = timeDao.insert(timeEntityList)

    suspend fun deleteAll() = timeDao.deleteAll()

    suspend fun getAllWeekdayTime(): List<TimeEntity> = timeDao.getAllWeekdayTime()

    suspend fun getAllWeekendTime(): List<TimeEntity> = timeDao.getAllWeekendTime()
}
