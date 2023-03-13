package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.BannerDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val bannerDataSource: BannerDataSource
) : BannerRepository {

    override suspend fun getActiveBanner(): List<Banner> {
        return bannerDataSource.getActiveBanner().map { bannerResponse -> bannerResponse.toModel()}
    }
}
