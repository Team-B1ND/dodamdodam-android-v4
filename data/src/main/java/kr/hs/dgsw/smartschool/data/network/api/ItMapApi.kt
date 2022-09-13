package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import retrofit2.http.GET
import retrofit2.http.Path

interface ItMapApi {

    @GET("itmap/companies")
    suspend fun getAllCompanies(): Response<List<Company>>

    @GET("itmap/company/{id}")
    suspend fun getCompanyById(
        @Path("id") id: Int
    ): Response<Company>

}