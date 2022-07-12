package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import javax.inject.Inject

class BusRemote @Inject constructor(
    val api: BusApi
) {
    suspend fun getBusList(
    ):Response<List<BusByDate>>{
        return api.getBusList()
    }
    suspend fun getMyBusList(
    ):Response<List<Bus>>{
        return api.getMyBus()
    }
    suspend fun getMyBusListMonth(
    ):Response<List<Bus>>{
        return api.getMyBusByMonth()
    }
    suspend fun updateBus(){}
    suspend fun updateBusApply(){}
    suspend fun addBus(){}
    suspend fun addBusApply(){}
    suspend fun deleteBus(){}
    suspend fun deleteBusApply(){}
}