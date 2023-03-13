package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import kr.hs.dgsw.smartschool.domain.param.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.param.out.OutRequest
import javax.inject.Inject

class OutDataSource @Inject constructor(
    override val remote: OutRemote,
    override val cache: Any
) : BaseDataSource<OutRemote, Any> {

    suspend fun getOutSleepingById(outSleepingId: Int): OutItemResponse = remote.getOutSleepingById(outSleepingId)

    suspend fun getMyOutSleeping(): List<OutItemResponse> = remote.getMyOutSleeping()

    suspend fun applyOutSleeping(request: OutRequest): OutItemResponse = remote.applyOutSleeping(request)

    suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItemResponse = remote.modifyOutSleeping(request)

    suspend fun deleteOutSleeping(outSleepingId: Int): String = remote.deleteOutSleeping(outSleepingId)

    suspend fun getOutGoingById(outGoingId: Int): OutItemResponse = remote.getOutGoingById(outGoingId)

    suspend fun getMyOutGoing(): List<OutItemResponse> = remote.getMyOutGoing()

    suspend fun applyOutGoing(request: OutRequest): OutItemResponse = remote.applyOutGoing(request)

    suspend fun modifyOutGoing(request: ModifyOutRequest): OutItemResponse = remote.modifyOutGoing(request)

    suspend fun deleteOutGoing(outGoingId: Int): String = remote.deleteOutGoing(outGoingId)
}
