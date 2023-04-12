package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.banner.BannerResponse
import kr.hs.dgsw.smartschool.data.network.response.banner.BannerStatusResponse
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.banner.BannerStatus

fun BannerResponse.toModel(): Banner = Banner(
    bannerOrder = this.bannerOrder,
    createDate = this.createDate,
    expiryDateTime = this.expiryDateTime,
    id = this.id,
    image = this.image,
    redirectUrl = this.redirectUrl,
    status = this.status.toModel(),
    title = this.title
)

fun BannerStatusResponse.toModel(): BannerStatus = when (this.name) {
    BannerStatus.ACTIVE.name -> BannerStatus.ACTIVE
    BannerStatus.DEACTIVATED.name -> BannerStatus.DEACTIVATED
    else -> BannerStatus.DEACTIVATED
}
