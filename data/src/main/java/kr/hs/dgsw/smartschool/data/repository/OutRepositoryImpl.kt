package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.OutDataSource
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import javax.inject.Inject

class OutRepositoryImpl @Inject constructor(
    private val outDataSource: OutDataSource
) : OutRepository {

    override suspend fun getAllOut(): List<OutItem> {
        val myOutSleeping = outDataSource.getMyOutSleeping()
        val myOutGoing = outDataSource.getMyOutGoing()

        val list = ArrayList<OutItem>()
        list.addAll(myOutSleeping)
        list.addAll(myOutGoing)
        return list.sortedBy { it.startOutDate }.filter { !it.isPassTime() }
    }

    override suspend fun getOutSleepingById(outSleepingId: Int): OutItem {
        return outDataSource.getOutSleepingById(outSleepingId)
    }

    override suspend fun getMyOutSleeping(): List<OutItem> {
        return outDataSource.getMyOutSleeping()
    }

    override suspend fun applyOutSleeping(request: OutRequest): OutItem {
        return outDataSource.applyOutSleeping(request)
    }

    override suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItem {
        return outDataSource.modifyOutSleeping(request)
    }

    override suspend fun deleteOutSleeping(outSleepingId: Int): String {
        return outDataSource.deleteOutSleeping(outSleepingId)
    }

    override suspend fun getOutGoingById(outGoingId: Int): OutItem {
        return outDataSource.getOutGoingById(outGoingId)
    }

    override suspend fun getMyOutGoing(): List<OutItem> {
        return outDataSource.getMyOutGoing()
    }

    override suspend fun applyOutGoing(request: OutRequest): OutItem {
        return outDataSource.applyOutGoing(request)
    }

    override suspend fun modifyOutGoing(request: ModifyOutRequest): OutItem {
        return outDataSource.modifyOutGoing(request)
    }

    override suspend fun deleteOutGoing(outGoingId: Int): String {
        return outDataSource.deleteOutGoing(outGoingId)
    }

}
