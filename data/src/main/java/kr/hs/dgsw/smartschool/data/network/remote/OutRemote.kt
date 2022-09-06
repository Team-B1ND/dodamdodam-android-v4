package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.OutApi
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import javax.inject.Inject

class OutRemote @Inject constructor(
    override val api: OutApi
) : BaseRemote<OutApi>() {

    suspend fun getOut(year: Int, month: Int, status: OutStatus): List<OutItem> =
        api.getOut(year, month, status).data.getAll()

    suspend fun getOutByDate(date: String): List<OutItem> =
        api.getOutByDate(date).data.getAll()

    suspend fun getOutSleepingById(outSleepingId: Int): OutItem =
        api.getOutSleepingById(outSleepingId).data

    suspend fun getMyOutSleeping(): List<OutItem> =
        api.getMyOutSleeping().data

    suspend fun applyOutSleeping(request: OutRequest): OutItem =
        api.applyOutSleeping(request).data

    suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItem =
        api.modifyOutSleeping(request).data

    suspend fun deleteOutSleeping(outSleepingId: Int): String =
        api.deleteOutSleeping(outSleepingId).message

    suspend fun getOutGoingById(outGoingId: Int): OutItem =
        api.getOutGoingById(outGoingId).data

    suspend fun getMyOutGoing(): List<OutItem> =
        api.getMyOutGoing().data

    suspend fun applyOutGoing(request: OutRequest): OutItem =
        api.applyOutGoing(request).data

    suspend fun modifyOutGoing(request: ModifyOutRequest): OutItem =
        api.modifyOutGoing(request).data

    suspend fun deleteOutGoing(outGoingId: Int): String =
        api.deleteOutGoing(outGoingId).message

}
