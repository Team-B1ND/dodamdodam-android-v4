package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import retrofit2.http.GET

interface BannerApi {

    @GET(DodamUrl.Banner.ACTIVE)
    suspend fun getActiveBanners(): Response<List<Banner>>
}
