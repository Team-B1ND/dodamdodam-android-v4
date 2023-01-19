package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.point.Point
import retrofit2.http.GET
import retrofit2.http.Query

interface PointApi {

    @GET(DodamUrl.Point.My.YEAR)
    suspend fun getMyYearPoints(
        @Query("year") year: Int
    ): Response<List<Point>>
}
