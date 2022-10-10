package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BusApi {
    @GET("api/bus")
    suspend fun getBusList(): Response<BusData<BusByDate>>

    @GET("api/bus/apply")
    suspend fun getMyBus(): Response<BusData<Bus>>

    @GET("api/bus/apply/month")
    suspend fun getMyBusByMonth(
        @Body month: Int,
        @Body year: Int
    ): Response<BusData<Bus>>

    @POST("api/bus")
    suspend fun addBus(
        @Body createBusDto : AddBusRequest
    ): Response<Any>

    @POST("api/bus/apply/{busId}")
    suspend fun addBusApply(
        @Path("busId") busId : Int
    ): Response<Any>

    @PATCH("bus")
    suspend fun updateBus(
        @Body busIdx: Int,
        @Body busName: String,
        @Body description: String,
        @Body leaveTime: String,
        @Body timeRequired: String,
        @Body peopleLimit: Int
    ): Response<Any>

    @PATCH("bus/self")
    suspend fun updateBusApply(
        @Body request: UpdateBusApplyRequest
    ): Response<Any>

    @DELETE("bus")
    suspend fun deleteBus(
        @Body idx: Int
    ): Response<Any>

    @DELETE("bus/self")
    suspend fun deleteBusApply(
        @Query("idx") busIdx: Int
    ): Response<Any>
}
