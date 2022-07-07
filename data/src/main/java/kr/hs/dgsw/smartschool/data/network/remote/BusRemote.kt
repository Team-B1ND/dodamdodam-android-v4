package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList
import javax.inject.Inject

class BusRemote @Inject constructor(
    val api: BusApi
) {
    suspend fun getBusList(
    ):List<BusList>{
        return api.getBusList().data
    }
    suspend fun getMyBusList(
    ):List<Bus>{
        return api.getMyBusList().data
    }
    suspend fun getMyBusListMonth(
    ):List<Bus>{
        return api.getMyBusListMonth().data
    }
}