package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OutApi {

    @GET("out/outsleeping/{id}")
    suspend fun getOutSleepingById(
        @Path("id") outSleepingId: Int
    ): Response<OutItem>

    @GET("out/outsleeping/my")
    suspend fun getMyOutSleeping(): Response<List<OutItem>>

    @POST("out/outsleeping")
    suspend fun applyOutSleeping(
        @Body request: OutRequest
    ): Response<OutItem>

    @PUT("out/outsleeping")
    suspend fun modifyOutSleeping(
        @Body request: ModifyOutRequest
    ): Response<OutItem>

    @DELETE("out/outsleeping/{outsleepingId}")
    suspend fun deleteOutSleeping(
        @Path("outsleepingId") outSleepingId: Int
    ): Response<Any>

    @GET("out/outgoing/{id}")
    suspend fun getOutGoingById(
        @Path("id") outGoingId: Int
    ): Response<OutItem>

    @GET("out/outgoing/my")
    suspend fun getMyOutGoing(): Response<List<OutItem>>

    @POST("out/outgoing")
    suspend fun applyOutGoing(
        @Body request: OutRequest
    ): Response<OutItem>

    @PUT("out/outgoing")
    suspend fun modifyOutGoing(
        @Body request: ModifyOutRequest
    ): Response<OutItem>

    @DELETE("out/outgoing/{outgoingId}")
    suspend fun deleteOutGoing(
        @Path("outgoingId") outGoingId: Int
    ): Response<Any>
}
