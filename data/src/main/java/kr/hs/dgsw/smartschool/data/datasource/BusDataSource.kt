package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import javax.inject.Inject

class BusDataSource @Inject constructor(
    override val cache: Any,
    override val remote: BusRemote
) : BaseDataSource<BusRemote, Any> {

    suspend fun getMyBusByMonth(
        year: Int,
        month: Int
    ): List<BusResponse> = remote.getMyBusByMonth(year, month).data

    suspend fun getMyBus(): BusResponse? {
        Log.e("BusDataSource", "getMyBus")
        return remote.getMyBus().data
    }

    suspend fun getBusList(): BusByDateResponse {
        return remote.getBusList().data
    }

    suspend fun updateBus(
        id: Int,
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): String {
        return remote.updateBus(id, busName, description, leaveTime, timeRequired, peopleLimit).message
    }

    suspend fun updateBusApply(
        busId: Int,
        originBusId: Int
    ): String {
        return remote.updateBusApply(busId, originBusId).message
    }

    suspend fun addBus(
        request: AddBusRequest
    ): String {
        return remote.addBus(request).message
    }

    suspend fun addBusApply(
        idx: Int
    ): String {
        return remote.addBusApply(idx).message
    }

    suspend fun deleteBus(
        idx: Int
    ): String {
        return remote.deleteBus(idx).message
    }

    suspend fun deleteBusApply(
        idx: Int
    ): String {
        return remote.deleteBusApply(idx).message
    }
}
