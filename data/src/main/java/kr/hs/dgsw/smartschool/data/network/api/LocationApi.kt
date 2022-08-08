package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.LocationData
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.Locations
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.LocationControlRequest
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import retrofit2.http.*

interface LocationApi {
    @POST("location")
    suspend fun postLocation(
        @Body request: LocationRequest
    ): Response<Any>

    @GET("location/my")
    suspend fun getMyLocation(
        @Query("date") date: String
    ): Response<LocationData>

    @GET("location/search")
    suspend fun getLocation(
        @Query("date") date: String
    ): Response<List<Locations>>

    @GET("location/default/{day}")
    suspend fun getDefaultLocation(
        @Path("day") day: Int
    ): Response<LocationData>

    @POST("location/default")
    suspend fun postDefaultLocation(
        @Body request: DefaultLocationRequest
    ): Response<Any>

    @PUT("location")
    suspend fun putAllLocation(
        @Body request: LocationRequest
    ): Response<Any>

    @PUT("location/{idx}")
    suspend fun putLocation(
        @Path("idx") idx: Int,
        @Body placeIdx: Location
    ) : Response<Any>

    @DELETE("location/{idx}")
    suspend fun deleteLocation(
        @Path("idx") idx: Int
    ): Response<Any>

    @PUT("location/check/{idx}")
    suspend fun checkLocation(
        @Path("idx") idx: Int
    ) : Response<Any>

    @PUT("location/uncheck/{idx}")
    suspend fun unCheckLocation(
        @Path("idx") idx: Int
    ): Response<Any>

    @POST("location/check")
    suspend fun postLocationControl(
        @Body request: LocationControlRequest
    ): Response<Any>
}