package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import javax.inject.Inject

class OutDataSource @Inject constructor(
    override val remote: OutRemote,
    override val cache: Any
) : BaseDataSource<OutRemote, Any> {

    suspend fun getOut(date: String): List<OutItem> = remote.getOut(date)

    suspend fun getOutAllows(date: String): List<OutItem> = remote.getOutAllows(date)

    suspend fun getOutSleepingById(outSleepingId: Int): OutSleeping = remote.getOutSleepingById(outSleepingId)

    suspend fun getOutGoingById(outGoingId: Int): OutGoing = remote.getOutGoingById(outGoingId)

    suspend fun postOutSleeping(request: OutRequest): String = remote.applyOutSleeping(request)

    suspend fun putOutSleeping(request: OutRequest): String = remote.modifyOutSleeping(request)

    suspend fun deleteOutSleeping(outSleepingOutIdx: Int): String = remote.deleteOutSleeping(outSleepingOutIdx)

    suspend fun postOutGoing(request: OutRequest): String = remote.applyOutGoing(request)

    suspend fun putOutGoing(request: OutRequest): String = remote.modifyOutGoing(request)

    suspend fun deleteOutGoing(outGoingIdx: Int): String = remote.deleteOutGoing(outGoingIdx)
}
