package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.TimeTableData
import retrofit2.http.GET

interface TimeApi {
    @GET("time-table")
    suspend fun getTimeTable(): Response<TimeTableData>
}