package kr.hs.dgsw.smartschool.domain.usecase.banner

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetActiveBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) : BaseUseCase<Unit, List<Banner>>() {

    override fun invoke(params: Unit): Flow<Resource<List<Banner>>> = execute {
        bannerRepository.getActiveBanner()
    }
}
