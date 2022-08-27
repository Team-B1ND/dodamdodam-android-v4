package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.TimeCache
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.data.mapper.TimeMapper
import kr.hs.dgsw.smartschool.data.network.remote.TimeRemote
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.model.time.WeekType
import kr.hs.dgsw.smartschool.domain.util.Utils
import javax.inject.Inject

class TimeDataSource @Inject constructor(
    override val remote: TimeRemote,
    override val cache: TimeCache
) : BaseDataSource<TimeRemote, TimeCache> {

    private val timeMapper = TimeMapper()

    suspend fun updateTimeList() =
        cache.deleteAll().also { insertAllTimeInfoRemote() }

    suspend fun getAllTime(): List<TimeEntity> =
        if (Utils.getWeekType() == WeekType.DAY)
            cache.getAllWeekdayTime().also { if (it.isEmpty()) getTimeRemote() }
        else
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }

    suspend fun getAllTime(dayOfWeek: Int): List<TimeEntity> =
        if (Utils.getWeekType(dayOfWeek) == WeekType.DAY)
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }
        else
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }

    private suspend fun insertAllTimeInfoRemote() =
        remote.getAllTime().also { insertTimeList(it) }

    private suspend fun getTimeRemote(): List<TimeEntity> =
        remote.getAllTime().map { time -> timeMapper.mapToEntity(time) }
            .also { timeEntityList -> cache.insertTime(timeEntityList) }

    private suspend fun insertTimeList(timeTableList: List<TimeTable>) =
        cache.insertTime(timeTableList.map { time -> timeMapper.mapToEntity(time) })
}
