package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.request.nightstudy.EveningStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EveningStudyApi {
    @DELETE(DodamUrl.EveningStudy.DELETE)
    suspend fun deleteEveningStudy(
        @Path("eveningStudyId") EveningStudyId: Int
    ): Response<Any>

    @POST(DodamUrl.EVENING_STUDY)
    suspend fun applyEveningStudy(
        @Body request: EveningStudyRequest
    ): Response<EveningStudyResponse>

    @GET(DodamUrl.EveningStudy.MY)
    suspend fun getMyEveningStudy(): Response<List<EveningStudyResponse>>
}
