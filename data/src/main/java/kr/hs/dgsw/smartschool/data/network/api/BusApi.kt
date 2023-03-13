package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BusApi {
    @GET(DodamUrl.BUS)
    suspend fun getBusList(): Response<BusByDateResponse>

    @GET(DodamUrl.Bus.APPLY)
    suspend fun getMyBus(): Response<BusResponse>

    @GET(DodamUrl.Bus.Apply.MONTH)
    suspend fun getMyBusByMonth(
        @Body month: Int,
        @Body year: Int
    ): Response<List<BusResponse>>

    @POST(DodamUrl.BUS)
    suspend fun addBus(
        @Body createBusDto: AddBusRequest
    ): Response<Any>

    @POST(DodamUrl.Bus.APPLY)
    suspend fun addBusApply(
        @Query("busId") busId: Int
    ): Response<Any>

    @PATCH(DodamUrl.Bus.ID)
    suspend fun updateBus(
        @Query("id") busId: Int,
        @Body request: UpdateBusRequest
    ): Response<Any>

    @PATCH(DodamUrl.Bus.APPLY)
    suspend fun updateBusApply(
        @Query("busId") busId: Int,
        @Query("originalBusId") originBusId: Int
    ): Response<Any>

    @DELETE(DodamUrl.Bus.ID)
    suspend fun deleteBus(
        @Path("id") idx: Int
    ): Response<Any>

    @DELETE(DodamUrl.Bus.Apply.BUS_ID)
    suspend fun deleteBusApply(
        @Path("busId") busId: Int
    ): Response<Any>
}
