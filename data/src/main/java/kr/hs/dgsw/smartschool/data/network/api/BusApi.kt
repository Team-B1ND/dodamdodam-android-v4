package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import retrofit2.http.*

interface BusApi {

    @GET("bus")
    suspend fun getBusList() : Response<BusData<BusByDate>>

    @GET("bus/self")
    suspend fun getMyBus() : Response<BusData<Bus>>

    @GET("bus/self/apply")
    suspend fun getMyBusByMonth(
        @Body myBusByMonthRequest: MyBusByMonthRequest
    ) : Response<BusData<Bus>>

    @POST("bus")
    suspend fun addBus(
       @Body addBusRequest: AddBusRequest
    ):Response<Any>

    @POST("bus/self")
    suspend fun addBusApply(
        @Body busIdx : Int
    ):Response<Any>


    @PUT("bus")
    suspend fun updateBus(
        @Body updateBusRequest: UpdateBusRequest
    ):Response<Any>

    @PUT("bus/self")
    suspend fun updateBusApply(
        @Body updateBusApplyRequest: UpdateBusApplyRequest
    ):Response<Any>


    @DELETE("bus")
    suspend fun deleteBus(
        @Body idx : Int
    ):Response<Any>

    @DELETE("bus/self")
    suspend fun deleteBusApply(
        @Body idx : Int
    ):Response<Any>
}