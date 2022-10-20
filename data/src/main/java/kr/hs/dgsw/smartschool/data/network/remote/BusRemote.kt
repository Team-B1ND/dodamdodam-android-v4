package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.bus.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.bus.UpdateBusRequest
import javax.inject.Inject

class BusRemote @Inject constructor(
    override val api: BusApi
) : BaseRemote<BusApi>() {

    suspend fun getBusList(): Response<BusByDate> = api.getBusList()

    suspend fun getMyBusList(): Response<List<Bus>> {
        Log.e("getMyBus", "실행")
        return api.getMyBus()
    }

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): Response<List<Bus>> = api.getMyBusByMonth(
        request.month,
        request.year
    )

    suspend fun updateBus(
        busId : Int,
        request: UpdateBusRequest
    ): Response<Any> = api.updateBus(
        busId,
        request
    )

    suspend fun updateBusApply(
        id:Int
    ): Response<Any> = api.updateBusApply(
        id
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
