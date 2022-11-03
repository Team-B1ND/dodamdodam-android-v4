package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.BannerRemote
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import javax.inject.Inject

class BannerDataSource @Inject constructor(
    override val remote: BannerRemote,
    override val cache: Any
) : BaseDataSource<BannerRemote, Any> {

    suspend fun getActiveBanner(): List<Banner> {
        return remote.getActiveBanner().data
    }

}