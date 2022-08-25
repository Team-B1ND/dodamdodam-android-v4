package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TimeDataSource
import kr.hs.dgsw.smartschool.data.mapper.TimeMapper
import kr.hs.dgsw.smartschool.domain.model.time.Time
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(
    private val timeDataSource: TimeDataSource
) : TimeRepository {

    private val timeMapper = TimeMapper()

    override suspend fun getAllTime(): List<Time> {
        return timeDataSource.getAllTime().map { timeEntity -> timeMapper.mapToModel(timeEntity) }
    }

    override suspend fun getStartTime(): String {
        return timeDataSource.getAllTime()[0].startTime
    }
}
