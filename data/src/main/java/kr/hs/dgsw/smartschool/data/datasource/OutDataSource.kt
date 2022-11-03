package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import javax.inject.Inject

class OutDataSource @Inject constructor(
    override val remote: OutRemote,
    override val cache: Any
) : BaseDataSource<OutRemote, Any> {

    suspend fun getOutSleepingById(outSleepingId: Int): OutItem = remote.getOutSleepingById(outSleepingId)

    suspend fun getMyOutSleeping(): List<OutItem> = remote.getMyOutSleeping()

    suspend fun applyOutSleeping(request: OutRequest): OutItem = remote.applyOutSleeping(request)

    suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItem = remote.modifyOutSleeping(request)

    suspend fun deleteOutSleeping(outSleepingId: Int): String = remote.deleteOutSleeping(outSleepingId)

    suspend fun getOutGoingById(outGoingId: Int): OutItem = remote.getOutGoingById(outGoingId)

    suspend fun getMyOutGoing(): List<OutItem> = remote.getMyOutGoing()

    suspend fun applyOutGoing(request: OutRequest): OutItem = remote.applyOutGoing(request)

    suspend fun modifyOutGoing(request: ModifyOutRequest): OutItem = remote.modifyOutGoing(request)

    suspend fun deleteOutGoing(outGoingId: Int): String = remote.deleteOutGoing(outGoingId)
}
