package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import javax.inject.Inject

class BusDataSource @Inject constructor(
    override val cache: Any,
    override val remote: BusRemote
) : BaseDataSource<BusRemote,Any>() {

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): BusData<Bus> = remote.getMyBusByMonth(request).data

    suspend fun getMyBusList(): BusData<Bus> = remote.getMyBusList().data

    suspend fun getBusList(): BusData<BusByDate> {
        return remote.getBusList().data
    }

    suspend fun updateBus(
        request: UpdateBusRequest
    ): String {
        Log.e("BusDataSource",return remote.updateBus(request).message)
    }

    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ): String {
        Log.e("BusDataSource",return remote.updateBusApply(request).message)
    }

    suspend fun addBus(
        request: AddBusRequest
    ): String {
        Log.e("BusDataSource",return remote.addBus(request).message)
    }

    suspend fun addBusApply(
        idx: Int
    ): String {
        Log.e("BusDataSource",return remote.addBusApply(idx).message)
    }

    suspend fun deleteBus(
        idx: Int
    ): String {
        Log.e("BusDataSource",return remote.deleteBus(idx).message)
    }

    suspend fun deleteBusApply(
        idx: Int
    ): String {
        Log.e("BusDataSource", return remote.deleteBusApply(idx).message)
    }
}