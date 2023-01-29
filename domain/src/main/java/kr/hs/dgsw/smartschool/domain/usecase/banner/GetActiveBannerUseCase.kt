package kr.hs.dgsw.smartschool.domain.usecase.banner

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetActiveBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) : noParamBaseUseCase<List<Banner>>() {

    override fun invoke(): Flow<Resource<List<Banner>>> = execute {
        bannerRepository.getActiveBanner()
    }
}
