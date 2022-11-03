package kr.hs.dgsw.smartschool.domain.model.banner

import com.google.gson.annotations.SerializedName

data class Banner(
    @field:SerializedName("bannerOrder") val bannerOrder: Int,
    @field:SerializedName("createDate") val createDate: String,
    @field:SerializedName("expiryDateTime") val expiryDateTime: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("redirectUrl") val redirectUrl: String,
    @field:SerializedName("status") val status: BannerStatus,
    @field:SerializedName("title") val title: String
)