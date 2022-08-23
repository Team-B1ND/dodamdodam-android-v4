package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.CreateBusDtoRequest
import kr.hs.dgsw.smartschool.domain.request.ModifyBusDtoRequest
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
        request: ModifyBusDtoRequest
    ): String {
        Log.e("BusDataSource", remote.updateBus(request).message)
        return remote.updateBus(request).message
    }

    suspend fun updateBusApply(
        request: ApplyBusDtoRequest
    ): String {
        Log.e("BusDataSource", remote.updateBusApply(request).message)
        return remote.updateBusApply(request).message
    }

    suspend fun addBus(
        request: CreateBusDtoRequest
    ): String {
        Log.e("BusDataSource", remote.addBus(request).message)
        return remote.addBus(request).message
    }

    suspend fun addBusApply(
        idx: Int
    ): String {
        Log.e("BusDataSource", remote.addBusApply(idx).message)
        return remote.addBusApply(idx).message
    }

    suspend fun deleteBus(
        idx: Int
    ): String {
        Log.e("BusDataSource", remote.deleteBus(idx).message)
        return remote.deleteBus(idx).message
    }

    suspend fun deleteBusApply(
        idx: Int
    ): String {
        Log.e("BusDataSource", remote.deleteBusApply(idx).message)
        return remote.deleteBusApply(idx).message
    }
}