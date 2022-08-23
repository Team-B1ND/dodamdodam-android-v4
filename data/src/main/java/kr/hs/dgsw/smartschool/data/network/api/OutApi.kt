package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.OutData
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import kr.hs.dgsw.smartschool.domain.request.OutRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OutApi {

    @GET("offbase/date")
    suspend fun getOut(
        @Query("date") date: String
    ): Response<OutData>

    @GET("offbase/leave/{leaveIdx}")
    suspend fun getOutSleepingById(
        @Path("leaveIdx") outSleepingIdx: Int
    ): Response<OutSleeping>

    @GET("offbase/pass/{passIdx}")
    suspend fun getOutGoingById(
        @Path("passIdx") outGoingIdx: Int
    ): Response<OutGoing>

    @POST("offbase/leave")
    suspend fun postOutSleeping(
        @Body request: OutRequest
    ): Response<Any>

    @PUT("offbase/leave")
    suspend fun putOutSleeping(
        @Body request: OutRequest
    ): Response<Any>

    @DELETE("offbase/leave")
    suspend fun deleteOutSleeping(
        @Query("idx") outGoingIdx: Int
    ): Response<Any>

    @POST("offbase/pass")
    suspend fun postOutGoing(
        @Body request: OutRequest
    ): Response<Any>

    @PUT("offbase/pass")
    suspend fun putOutGoing(
        @Body request: OutRequest
    ): Response<Any>

    @DELETE("offbase/pass")
    suspend fun deleteOutGoing(
        @Query("idx") outGoingIdx: Int
    ): Response<Any>
}