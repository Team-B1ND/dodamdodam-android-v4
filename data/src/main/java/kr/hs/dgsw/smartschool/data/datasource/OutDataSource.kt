package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import kr.hs.dgsw.smartschool.domain.request.OutRequest
import javax.inject.Inject

class OutDataSource @Inject constructor(
    override val remote: OutRemote,
    override val cache: Any
) : BaseDataSource<OutRemote, Any>() {

    suspend fun getOut(date: String): List<OutItem> = remote.getOut(date)

    suspend fun getOutAllows(date: String): List<OutItem> = remote.getOutAllows(date)

    suspend fun getOutSleepingById(outSleepingIdx: Int): OutSleeping = remote.getOutSleepingById(outSleepingIdx)

    suspend fun getOutGoingById(outGoingIdx: Int): OutGoing = remote.getOutGoingById(outGoingIdx)

    suspend fun postOutSleeping(request: OutRequest): String = remote.postOutSleeping(request)

    suspend fun putOutSleeping(request: OutRequest): String = remote.putOutSleeping(request)

    suspend fun deleteOutSleeping(outSleepingOutIdx: Int): String = remote.deleteOutSleeping(outSleepingOutIdx)

    suspend fun postOutGoing(request: OutRequest): String = remote.postOutGoing(request)

    suspend fun putOutGoing(request: OutRequest): String = remote.putOutGoing(request)

    suspend fun deleteOutGoing(outGoingIdx: Int): String = remote.deleteOutGoing(outGoingIdx)
}
