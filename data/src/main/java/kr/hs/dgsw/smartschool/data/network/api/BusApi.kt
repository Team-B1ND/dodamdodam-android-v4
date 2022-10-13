package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.BusData
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.bus.UpdateBusRequest
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

    @PATCH("api/bus/{id}")
    suspend fun updateBus(
        @Query("id") busId: Int,
        @Body request: UpdateBusRequest
    ): Response<Any>

    @PATCH("api/bus/apply/{busId}")
    suspend fun updateBusApply(
        @Path("busId") busId:Int
    ): Response<Any>

    @DELETE("api/bus/{id}")
    suspend fun deleteBus(
        @Path("id") idx: Int
    ): Response<Any>

    @DELETE("api/bus/apply/{busId}")
    suspend fun deleteBusApply(
        @Path("busId") busId: Int
    ): Response<Any>
}
