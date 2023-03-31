package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.OutDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.param.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.param.out.OutRequest
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class OutRepositoryImpl @Inject constructor(
    private val outDataSource: OutDataSource
) : OutRepository {

    override suspend fun getAllOut(): List<OutItem> {
        val myOutSleeping = outDataSource.getMyOutSleeping()
        val myOutGoing = outDataSource.getMyOutGoing()

        val list = ArrayList<OutItemResponse>()
        list.addAll(myOutSleeping)
        list.addAll(myOutGoing)
        return list.sortedBy { it.startOutDate }.filter { !it.isPassTime() }.map { outItemResponse -> outItemResponse.toModel() }
    }

    override suspend fun getOutSleepingById(outSleepingId: Int): OutItem {
        return outDataSource.getOutSleepingById(outSleepingId).toModel()
    }

    override suspend fun getMyOutSleeping(): List<OutItem> {
        return outDataSource.getMyOutSleeping().map { outItemResponse -> outItemResponse.toModel() }
    }

    override suspend fun applyOutSleeping(request: OutRequest): OutItem {
        return outDataSource.applyOutSleeping(request).toModel()
    }

    override suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItem {
        return outDataSource.modifyOutSleeping(request).toModel()
    }

    override suspend fun deleteOutSleeping(outSleepingId: Int): String {
        return outDataSource.deleteOutSleeping(outSleepingId)
    }

    override suspend fun getOutGoingById(outGoingId: Int): OutItem {
        return outDataSource.getOutGoingById(outGoingId).toModel()
    }

    override suspend fun getMyOutGoing(): List<OutItem> {
        return outDataSource.getMyOutGoing().map { outItemResponse -> outItemResponse.toModel() }
    }

    override suspend fun applyOutGoing(request: OutRequest): OutItem {
        return outDataSource.applyOutGoing(request).toModel()
    }

    override suspend fun modifyOutGoing(request: ModifyOutRequest): OutItem {
        return outDataSource.modifyOutGoing(request).toModel()
    }

    override suspend fun deleteOutGoing(outGoingId: Int): String {
        return outDataSource.deleteOutGoing(outGoingId)
    }
}
