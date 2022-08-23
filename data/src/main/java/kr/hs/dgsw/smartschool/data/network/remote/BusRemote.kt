package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.CreateBusDtoRequest
import kr.hs.dgsw.smartschool.domain.request.ModifyBusDtoRequest
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
        request: ModifyBusDtoRequest
    ):Response<Any> = api.updateBus(request)

    suspend fun updateBusApply(
        request: ApplyBusDtoRequest
    ):Response<Any> = api.updateBusApply(request)

    suspend fun addBus(
        request: CreateBusDtoRequest
    ):Response<Any> = api.addBus(request)

    suspend fun addBusApply(
        idx:Int
    ) :Response<Any> = api.addBusApply(idx)

    suspend fun deleteBus(
        idx : Int
    ):Response<Any> = api.deleteBus(idx)

    suspend fun deleteBusApply(
        idx : Int
    ):Response<Any> = api.deleteBusApply(idx)
}