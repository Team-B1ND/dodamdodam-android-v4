package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList
import retrofit2.http.GET
import retrofit2.http.Query

interface BusApi {
    @GET("bus")
    suspend fun getBusList(
    ):Response<List<BusList>>
    //TODO 반환형 고치기
}