package kr.hs.dgsw.smartschool.dodamdodam.features.home.state

import kr.hs.dgsw.smartschool.domain.model.banner.Banner

data class GetActiveBannerState(
    val activeBanners: List<Banner> = emptyList(),
    val error: String = ""
)
