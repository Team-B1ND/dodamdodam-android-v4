package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.OutApi
import kr.hs.dgsw.smartschool.data.network.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.data.network.request.out.OutRequest
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import javax.inject.Inject

class OutRemote @Inject constructor(
    override val api: OutApi
) : BaseRemote<OutApi>() {

    suspend fun getOutSleepingById(outSleepingId: Int): OutItemResponse =
        api.getOutSleepingById(outSleepingId).data

    suspend fun getMyOutSleeping(): List<OutItemResponse> =
        api.getMyOutSleeping().data

    suspend fun applyOutSleeping(request: OutRequest): OutItemResponse =
        api.applyOutSleeping(request).data

    suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItemResponse =
        api.modifyOutSleeping(request).data

    suspend fun deleteOutSleeping(outSleepingId: Int): String =
        api.deleteOutSleeping(outSleepingId).message

    suspend fun getOutGoingById(outGoingId: Int): OutItemResponse =
        api.getOutGoingById(outGoingId).data

    suspend fun getMyOutGoing(): List<OutItemResponse> =
        api.getMyOutGoing().data

    suspend fun applyOutGoing(request: OutRequest): OutItemResponse =
        api.applyOutGoing(request).data

    suspend fun modifyOutGoing(request: ModifyOutRequest): OutItemResponse =
        api.modifyOutGoing(request).data

    suspend fun deleteOutGoing(outGoingId: Int): String =
        api.deleteOutGoing(outGoingId).message
}
