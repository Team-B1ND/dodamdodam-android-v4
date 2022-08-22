package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import javax.inject.Inject

class BusRemote @Inject constructor(
    override val api: BusApi
) : BaseRemote<BusApi>() {

    suspend fun getBusList(): Response<BusData<BusByDate>> = api.getBusList()

    suspend fun getMyBusList() : Response<BusData<Bus>> = api.getMyBus()

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ) : Response<BusData<Bus>> = api.getMyBusByMonth(request)

    suspend fun updateBus(
        request: UpdateBusRequest
    ) : String = api.updateBus(request).message

    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ):String = api.updateBusApply(request).message

    suspend fun addBus(
        request: AddBusRequest
    ): String = api.addBus(request).message

    suspend fun addBusApply(
        idx:Int
    ):String = api.addBusApply(idx).message

    suspend fun deleteBus(
        idx : Int
    ): String = api.deleteBus(idx).message

    suspend fun deleteBusApply(
        idx : Int
    ): String = api.deleteBusApply(idx).message
}