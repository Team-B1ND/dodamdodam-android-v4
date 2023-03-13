package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.BannerApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.banner.BannerResponse
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import javax.inject.Inject

class BannerRemote @Inject constructor(
    override val api: BannerApi
) : BaseRemote<BannerApi>() {

    suspend fun getActiveBanner(): List<BannerResponse> {
        return api.getActiveBanners().data
    }
}
