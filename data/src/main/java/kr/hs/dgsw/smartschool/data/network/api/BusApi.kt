package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import retrofit2.http.*
import java.time.Month

interface BusApi {
    @GET("bus")
    suspend fun getBusList(): Response<BusData<BusByDate>>

    @GET("bus/self")
    suspend fun getMyBus(): Response<BusData<Bus>>

    @GET("bus/self/apply")
    suspend fun getMyBusByMonth(
        @Body month : Int,
        @Body year : Int
    ) : Response<BusData<Bus>>

    @POST("bus")
    suspend fun addBus(
       @Body busName: String,
       @Body description : String,
       @Body leaveTime : String,
       @Body timeRequired : String,
       @Body peopleLimit : Int
    ):Response<Any>

    @POST("bus/self")
    suspend fun addBusApply(
        @Body addBusApplyRequest: AddBusApplyRequest
    ):Response<Any>

    @PUT("bus")
    suspend fun updateBus(
        @Body busIdx : Int,
        @Body busName: String,
        @Body description : String,
        @Body leaveTime : String,
        @Body timeRequired : String,
        @Body peopleLimit : Int
    ):Response<Any>

    @PUT("bus/self")
    suspend fun updateBusApply(
        @Body request : UpdateBusApplyRequest
    ):Response<Any>

    @DELETE("bus")
    suspend fun deleteBus(
        @Body idx: Int
    ): Response<Any>

    @DELETE("bus/self")
    suspend fun deleteBusApply(
        @Query("idx") busIdx: Int
    ):Response<Any>
}
