package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.out.OutItemResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.param.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.param.out.OutRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OutApi {

    @GET(DodamUrl.Out.OutSleeping.ID)
    suspend fun getOutSleepingById(
        @Path("id") outSleepingId: Int
    ): Response<OutItemResponse>

    @GET(DodamUrl.Out.OutSleeping.MY)
    suspend fun getMyOutSleeping(): Response<List<OutItemResponse>>

    @POST(DodamUrl.Out.OUT_SLEEPING)
    suspend fun applyOutSleeping(
        @Body request: OutRequest
    ): Response<OutItemResponse>

    @PUT(DodamUrl.Out.OUT_SLEEPING)
    suspend fun modifyOutSleeping(
        @Body request: ModifyOutRequest
    ): Response<OutItemResponse>

    @DELETE(DodamUrl.Out.OutSleeping.OUT_SLEEPING_ID)
    suspend fun deleteOutSleeping(
        @Path("outsleepingId") outSleepingId: Int
    ): Response<Any>

    @GET(DodamUrl.Out.OutGoing.ID)
    suspend fun getOutGoingById(
        @Path("id") outGoingId: Int
    ): Response<OutItemResponse>

    @GET(DodamUrl.Out.OutGoing.MY)
    suspend fun getMyOutGoing(): Response<List<OutItemResponse>>

    @POST(DodamUrl.Out.OUT_GOING)
    suspend fun applyOutGoing(
        @Body request: OutRequest
    ): Response<OutItemResponse>

    @PUT(DodamUrl.Out.OUT_GOING)
    suspend fun modifyOutGoing(
        @Body request: ModifyOutRequest
    ): Response<OutItemResponse>

    @DELETE(DodamUrl.Out.OutGoing.OUT_GOING_ID)
    suspend fun deleteOutGoing(
        @Path("outgoingId") outGoingId: Int
    ): Response<Any>
}
