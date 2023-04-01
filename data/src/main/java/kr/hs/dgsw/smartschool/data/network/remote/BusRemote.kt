package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.data.network.request.bus.UpdateBusRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyParam
import javax.inject.Inject

class BusRemote @Inject constructor(
    override val api: BusApi
) : BaseRemote<BusApi>() {

    suspend fun getBusList(): Response<BusByDateResponse> = api.getBusList()

    suspend fun getMyBus(): Response<BusResponse> {
        Log.e("getMyBus", "실행")
        return api.getMyBus()
    }

    suspend fun getMyBusByMonth(
        request: MyBusByMonthParam
    ): Response<List<BusResponse>> = api.getMyBusByMonth(
        request.month,
        request.year
    )

    suspend fun updateBus(
        busId: Int,
        request: UpdateBusRequest
    ): Response<Any> = api.updateBus(
        busId,
        request
    )

    suspend fun updateBusApply(
        request: UpdateBusApplyParam
    ): Response<Any> = api.updateBusApply(
        request.busId,
        request.originBusId
    )

    suspend fun addBus(
        request: AddBusRequest
    ): Response<Any> = api.addBus(
        request
    )

    suspend fun addBusApply(
        idx: Int
    ): Response<Any> {
        Log.e("addBusApply", "실행")
        return api.addBusApply(idx)
    }

    suspend fun deleteBus(
        idx: Int
    ): Response<Any> = api.deleteBus(idx)

    suspend fun deleteBusApply(
        idx: Int
    ): Response<Any> = api.deleteBusApply(idx)
}
