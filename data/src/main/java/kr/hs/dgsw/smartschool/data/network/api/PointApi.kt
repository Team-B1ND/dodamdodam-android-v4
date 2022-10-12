package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface PointApi {

    @GET("point/my/year")
    suspend fun getMyYearPoints(
        @Query("year") year: Int
    ): Response<List<MyYearPoint>>
}
