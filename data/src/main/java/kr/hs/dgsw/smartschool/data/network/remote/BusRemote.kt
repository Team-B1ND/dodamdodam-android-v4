package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.data.network.request.bus.UpdateBusRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import javax.inject.Inject

class BusRemote @Inject constructor(
    override val api: BusApi
) : BaseRemote<BusApi>() {

    suspend fun getBusList(): Response<BusByDateResponse> = api.getBusList()

    suspend fun getMyBus(): Response<BusResponse?> {
        Log.e("getMyBus", "실행")
        return api.getMyBus()
    }

    suspend fun getMyBusByMonth(
        year: Int,
        month: Int
    ): Response<List<BusResponse>> = api.getMyBusByMonth(
        month,
        year
    )

    suspend fun updateBus(
        busId: Int,
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): Response<Any> = api.updateBus(
        UpdateBusRequest(
            busId,
            busName,
            description,
            leaveTime,
            timeRequired,
            peopleLimit
        )
    )

    suspend fun updateBusApply(
        busId: Int,
        originBusId: Int
    ): Response<Any> = api.updateBusApply(
        busId,
        originBusId
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
