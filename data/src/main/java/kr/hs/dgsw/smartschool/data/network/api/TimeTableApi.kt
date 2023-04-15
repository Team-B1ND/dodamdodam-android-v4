package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.time.TimeTableResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.GET

interface TimeTableApi {
    @GET(DodamUrl.Time.TABLES)
    suspend fun getTimeTables(): Response<List<TimeTableResponse>>
}
