package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.request.nightstudy.NightStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NightStudyApi {
    @DELETE(DodamUrl.NightStudy.DELETE)
    suspend fun deleteNightStudy(
        @Path("id") NightStudyId: Int
    ): Response<Any>

    @POST(DodamUrl.NIGHT_STUDY)
    suspend fun applyNightStudy(
        @Body request: NightStudyRequest
    ): Response<NightStudyResponse>

    @GET(DodamUrl.NightStudy.MY)
    suspend fun getMyNightStudy(): Response<List<NightStudyResponse>>
}
