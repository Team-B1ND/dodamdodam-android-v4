package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList
import kr.hs.dgsw.smartschool.domain.model.bus.BusMember
import retrofit2.http.*

interface BusApi {
    @GET("bus")
    suspend fun getBusList(
    ):Response<List<BusList>>
    //도담 티처
    /*@POST("bus")
    suspend fun addBus(
        @Body busName: String,
        @Body description : String,
        @Body leaveTime : String,
        @Body timeRequired : String,
        @Body peopleLimit : Int
    ):Response<Any>
    @PUT("bus")
    suspend fun updateBus(
        @Body busIdx : Int,
        @Body busName : String,
        @Body description: String,
        @Body leaveTime: String,
        @Body timeRequired: String,
        @Body peopleLimit: Int
    ):Response<Any>
    @DELETE("bus")
    suspend fun deleteBus(
        @Body idx : Int
    ):Response<Any>*/
    @GET("bus/self")
    suspend fun getMyBusList(
    ):Response<List<Bus>>
    @GET("bus/self/apply")
    suspend fun getMyBusListMonth(
    ):Response<List<Bus>>
    @POST("bus/self")
    suspend fun applyBus(
        @Body busIdx : Int
    ):Response<Any>
    @PUT("bus/self")
    suspend fun modifyBus(
        @Body originBusIdx : Int,
        @Body busIdx: Int
    ):Response<Any>
    @DELETE("bus/self")
    suspend fun deleteBus(
        @Body idx : Int
    ):Response<Any>
}