package kr.hs.dgsw.smartschool.domain.model.banner

data class Banner(
    val bannerOrder: Int,
    val createDate: String,
    val expiryDateTime: String,
    val id: Int,
    val image: String,
    val redirectUrl: String,
    val status: BannerStatus,
    val title: String
)
