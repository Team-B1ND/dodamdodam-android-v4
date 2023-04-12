package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BannerRemote
import kr.hs.dgsw.smartschool.data.network.response.banner.BannerResponse
import javax.inject.Inject

class BannerDataSource @Inject constructor(
    override val remote: BannerRemote,
    override val cache: Any
) : BaseDataSource<BannerRemote, Any> {

    suspend fun getActiveBanner(): List<BannerResponse> {
        return remote.getActiveBanner()
    }
}
