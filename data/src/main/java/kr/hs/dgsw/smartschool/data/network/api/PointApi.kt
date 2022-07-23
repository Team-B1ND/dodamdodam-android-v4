package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.PointData
import kr.hs.dgsw.smartschool.domain.model.point.MyTargetPoint
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface PointApi {
    @GET("point/my/thisyear")
    suspend fun getMyPoint(
        @Query("year") year: String,
        @Query("type") type: Int
    ): Response<PointData<MyYearPoint>>

    @GET("point/my/target")
    suspend fun getMyPointTarget(
        @Query("target") target: Int
    ): Response<PointData<MyTargetPoint>>
}