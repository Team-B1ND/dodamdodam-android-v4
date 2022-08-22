package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.BusCache
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
    override val cache: BusCache,
    override val remote: BusRemote
) : BaseDataSource<BusRemote,Any>() {

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): BusData<Bus>
    = remote.getMyBusByMonth(request).data

    suspend fun getMyBusList(): BusData<Bus>
    = remote.getMyBusList().data

    suspend fun getBusList(): BusData<BusByDate> {
        return remote.getBusList().data
    }

    suspend fun updateBus(
        request: UpdateBusRequest
    ) = remote.updateBus(request)

    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ) = remote.updateBusApply(request)

    suspend fun addBus(
        request: AddBusRequest
    ) = remote.addBus(request)

    suspend fun addBusApply(
        idx:Int
    )
    = remote.addBusApply(idx)

    suspend fun deleteBus(
        idx : Int
    ) = remote.deleteBus(idx)

    suspend fun deleteBusApply(
        idx: Int
    ) = remote.deleteBusApply(idx)
}