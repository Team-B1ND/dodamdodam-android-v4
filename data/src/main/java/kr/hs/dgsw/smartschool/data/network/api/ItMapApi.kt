package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.itmap.CompanyResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import retrofit2.http.GET
import retrofit2.http.Path

interface ItMapApi {

    @GET(DodamUrl.Itmap.COMPANIES)
    suspend fun getAllCompanies(): Response<List<CompanyResponse>>

    @GET(DodamUrl.Itmap.Company.ID)
    suspend fun getCompanyById(
        @Path("id") id: Int
    ): Response<CompanyResponse>
}
