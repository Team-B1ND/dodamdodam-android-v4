package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.OutApi
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import javax.inject.Inject

class OutRemote @Inject constructor(
    override val api: OutApi
) : BaseRemote<OutApi>() {

    suspend fun getOut(date: String): List<OutItem> =
        api.getOut(date).data.getAll()

    suspend fun getOutAllows(date: String): List<OutItem> =
        api.getOut(date).data.getAllows()

    suspend fun getOutSleepingById(outSleepingIdx: Int): OutSleeping =
        api.getOutSleepingById(outSleepingIdx).data

    suspend fun getOutGoingById(outGoingIdx: Int): OutGoing =
        api.getOutGoingById(outGoingIdx).data

    suspend fun postOutSleeping(request: OutRequest): String =
        api.applyOutSleeping(request).message

    suspend fun putOutSleeping(request: OutRequest): String =
        api.modifyOutSleeping(request).message

    suspend fun deleteOutSleeping(outSleepingIdx: Int): String =
        api.deleteOutSleeping(outSleepingIdx).message

    suspend fun postOutGoing(request: OutRequest): String =
        api.applyOutGoing(request).message

    suspend fun putOutGoing(request: OutRequest): String =
        api.modifyOutGoing(request).message

    suspend fun deleteOutGoing(outGoingIdx: Int): String =
        api.deleteOutGoing(outGoingIdx).message
}
