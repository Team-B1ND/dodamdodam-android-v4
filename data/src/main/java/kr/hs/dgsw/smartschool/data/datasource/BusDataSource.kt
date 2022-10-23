package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.bus.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.bus.UpdateBusRequest
import javax.inject.Inject

class BusDataSource @Inject constructor(
    override val cache: Any,
    override val remote: BusRemote
) : BaseDataSource<BusRemote, Any> {

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): List<Bus> = remote.getMyBusByMonth(request).data

    suspend fun getMyBusList(): List<Bus> {
        Log.e("BusDataSource", "getMyBus")
        return remote.getMyBusList().data
    }

    suspend fun getBusList(): BusByDate {
        return remote.getBusList().data
    }

    suspend fun updateBus(
        id: Int,
        request: UpdateBusRequest
    ): String {
        return remote.updateBus(id, request).message
    }

    suspend fun updateBusApply(
        id: Int
    ): String {
        return remote.updateBusApply(id).message
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
