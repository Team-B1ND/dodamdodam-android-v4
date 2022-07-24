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

    suspend fun getBusList(): BusData<BusByDate> = api.getBusList().data

    suspend fun getMyBusList() : BusData<Bus> = api.getMyBus().data

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ) : BusData<Bus> = api.getMyBusByMonth(request).data

    suspend fun updateBus(
        request: UpdateBusRequest
    ){}
    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ){}
    suspend fun addBus(
        request: AddBusRequest
    ){}
    suspend fun addBusApply(){}
    suspend fun deleteBus(
        idx : Int
    ){}
    suspend fun deleteBusApply(
        idx : Int
    ){}
}