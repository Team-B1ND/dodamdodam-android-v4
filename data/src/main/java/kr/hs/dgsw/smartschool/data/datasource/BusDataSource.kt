package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.data.network.request.bus.UpdateBusRequest
import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyParam
import javax.inject.Inject

class BusDataSource @Inject constructor(
    override val cache: Any,
    override val remote: BusRemote
) : BaseDataSource<BusRemote, Any> {

    suspend fun getMyBusByMonth(
        request: MyBusByMonthParam
    ): List<BusResponse> = remote.getMyBusByMonth(request).data

    suspend fun getMyBus(): BusResponse {
        Log.e("BusDataSource", "getMyBus")
        return remote.getMyBus().data
    }

    suspend fun getBusList(): BusByDateResponse {
        return remote.getBusList().data
    }

    suspend fun updateBus(
        id: Int,
        request: UpdateBusRequest
    ): String {
        return remote.updateBus(id, request).message
    }

    suspend fun updateBusApply(
        request: UpdateBusApplyParam
    ): String {
        return remote.updateBusApply(request).message
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
