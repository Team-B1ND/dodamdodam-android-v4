package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.TimeTableCache
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.remote.TimeTableRemote
import kr.hs.dgsw.smartschool.data.network.response.time.TimeTableResponse
import kr.hs.dgsw.smartschool.domain.model.time.WeekType
import kr.hs.dgsw.smartschool.domain.util.Utils
import javax.inject.Inject

class TimeTableDataSource @Inject constructor(
    override val remote: TimeTableRemote,
    override val cache: TimeTableCache
) : BaseDataSource<TimeTableRemote, TimeTableCache> {

    suspend fun updateTimeList() =
        cache.deleteAll().also { insertAllTimeInfoRemote() }

    suspend fun getAllTime(): List<TimeEntity> =
        if (Utils.getWeekType() == WeekType.WEEKDAY)
            cache.getAllWeekdayTime().also { if (it.isEmpty()) getTimeRemote() }
        else
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }

    suspend fun getAllTime(dayOfWeek: Int): List<TimeEntity> =
        if (Utils.getWeekType(dayOfWeek) == WeekType.WEEKDAY)
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }
        else
            cache.getAllWeekendTime().also { if (it.isEmpty()) getTimeRemote() }

    private suspend fun insertAllTimeInfoRemote() =
        remote.getAllTime().also { insertTimeList(it) }

    private suspend fun getTimeRemote(): List<TimeEntity> =
        remote.getAllTime().map { time -> time.toModel().toEntity() }
            .also { timeEntityList -> cache.insertTime(timeEntityList) }

    private suspend fun insertTimeList(timeTableList: List<TimeTableResponse>) =
        cache.insertTime(timeTableList.map { time -> time.toModel().toEntity() })
}
