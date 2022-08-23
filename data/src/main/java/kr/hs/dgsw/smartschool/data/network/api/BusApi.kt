package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.CreateBusDtoRequest
import kr.hs.dgsw.smartschool.domain.request.BusDtoRequest
import kr.hs.dgsw.smartschool.domain.request.ModifyBusDtoRequest
import retrofit2.http.*
import java.time.Month

interface BusApi {
    @GET("bus")
    suspend fun getBusList() : Response<BusData<BusByDate>>

    @GET("bus/apply")
    suspend fun getMyBus() : Response<BusData<Bus>>

    @GET("bus/apply/month")
    suspend fun getMyBusByMonth(
        @Body year : Int,
        @Body month : Month
    ) : Response<BusData<Bus>>

    @POST("bus")
    suspend fun addBus(
       @Body createBusDto: CreateBusDtoRequest
    ):Response<Any>

    @POST("bus/apply")
    suspend fun addBusApply(
        @Body busDto : BusDtoRequest
    ):Response<Any>


    @PATCH("bus/{id}")
    suspend fun updateBus(
        @Path("id") idx : Int,
        @Body modifyBusDto: ModifyBusDtoRequest
    ):Response<Any>

    @PATCH("bus/apply")
    suspend fun updateBusApply(
        @Body busDto: BusDtoRequest
    ):Response<Any>


    @DELETE("bus/{id}")
    suspend fun deleteBus(
        @Path("id") idx : Int
    ):Response<Any>

    @DELETE("bus/apply")
    suspend fun deleteBusApply(
        @Body applyBusDto : BusDtoRequest
    ):Response<Any>
}