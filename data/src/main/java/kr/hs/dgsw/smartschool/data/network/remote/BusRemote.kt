package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import javax.inject.Inject

class BusRemote @Inject constructor(
    override val api: BusApi
) : BaseRemote<BusApi>() {

    suspend fun getBusList(): Response<BusData<BusByDate>> = api.getBusList()

    suspend fun getMyBusList() : Response<BusData<Bus>> {
        Log.e("getMyBus","실행")
        return api.getMyBus()
    }

    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ) : Response<BusData<Bus>> = api.getMyBusByMonth(
        request.month,
        request.year
    )

    suspend fun updateBus(
        request: UpdateBusRequest
    ):Response<Any> = api.updateBus(
        request.idx,
        request.busName,
        request.description,
        request.leaveTime,
        request.timeRequired,
        request.peopleLimit
    )

    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ):Response<Any> = api.updateBusApply(
        UpdateBusApplyRequest(
            request.busIdx,
            request.originBusIdx)
    )

    suspend fun addBus(
        request: AddBusRequest
    ):Response<Any> = api.addBus(
        request.busName,
        request.description,
        request.leaveTime,
        request.timeRequired,
        request.peopleLimit
    )

    suspend fun addBusApply(
        idx:Int
    ) :Response<Any> {
        Log.e("addBusApply","실행")
        return api.addBusApply(AddBusApplyRequest(idx.toString()))
    }

    suspend fun deleteBus(
        idx : Int
    ):Response<Any> = api.deleteBus(idx)

    suspend fun deleteBusApply(
        idx : Int
    ):Response<Any> = api.deleteBusApply(idx)
}