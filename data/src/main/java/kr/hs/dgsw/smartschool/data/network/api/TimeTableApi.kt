package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.TimeTableData
import retrofit2.http.GET
import retrofit2.http.Path

interface TimeTableApi {
    @GET("time/tables")
    suspend fun getTimeTables(): Response<TimeTableData>
}
