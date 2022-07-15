package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.PointData
import kr.hs.dgsw.smartschool.domain.model.point.MyPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface PointApi {
    @GET("point/my/thisyear")
    suspend fun getMyPoint(
        @Query("year") year: String,
        @Query("type") type: Int
    ): Response<PointData<MyPoint>>

    @GET("point/my/target")
    suspend fun getMyPointTarget(
        @Query("target") target: Int
    ): Response<PointData<MyPoint>>
}