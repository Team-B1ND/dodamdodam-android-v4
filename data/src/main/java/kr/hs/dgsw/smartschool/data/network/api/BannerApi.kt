package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import retrofit2.http.GET

interface BannerApi {

    @GET("banner/active")
    suspend fun getActiveBanners() : Response<List<Banner>>
}