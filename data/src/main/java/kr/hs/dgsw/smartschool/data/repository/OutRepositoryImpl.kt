package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.OutDataSource
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.request.OutRequest
import javax.inject.Inject

class OutRepositoryImpl @Inject constructor(
    private val outDataSource: OutDataSource
) : OutRepository {

    override suspend fun getOut(date: String): List<OutItem> {
        return outDataSource.getOut(date)
    }

    override suspend fun getOutAllows(date: String): List<OutItem> {
        return outDataSource.getOutAllows(date)
    }

    override suspend fun getOutSleepingById(outSleepingIdx: Int): OutSleeping {
        return outDataSource.getOutSleepingById(outSleepingIdx)
    }

    override suspend fun getOutGoingById(outGoingIdx: Int): OutGoing {
        return outDataSource.getOutGoingById(outGoingIdx)
    }

    override suspend fun postOutSleeping(request: OutRequest): String {
        return outDataSource.postOutSleeping(request)
    }

    override suspend fun putOutSleeping(request: OutRequest): String {
        return outDataSource.putOutSleeping(request)
    }

    override suspend fun deleteOutSleeping(outSleepingIdx: Int): String {
        return outDataSource.deleteOutSleeping(outSleepingIdx)
    }

    override suspend fun postOutGoing(request: OutRequest): String {
        return outDataSource.postOutGoing(request)
    }

    override suspend fun putOutGoing(request: OutRequest): String {
        return outDataSource.putOutGoing(request)
    }

    override suspend fun deleteOutGoing(outGoingIdx: Int): String {
        return outDataSource.deleteOutGoing(outGoingIdx)
    }
}
