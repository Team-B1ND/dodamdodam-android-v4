package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TimeTableDataSource
import kr.hs.dgsw.smartschool.data.mapper.TimeTableMapper
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(
    private val timeTableDataSource: TimeTableDataSource
) : TimeRepository {

    private val timeTableMapper = TimeTableMapper()

    override suspend fun getAllTime(): List<TimeTable> {
        return timeTableDataSource.getAllTime().map { timeEntity -> timeTableMapper.mapToModel(timeEntity) }
    }

    override suspend fun getStartTime(): String {
        return timeTableDataSource.getAllTime()[0].startTime
    }
}
