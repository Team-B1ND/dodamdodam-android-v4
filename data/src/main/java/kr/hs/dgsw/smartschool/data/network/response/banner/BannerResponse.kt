package kr.hs.dgsw.smartschool.data.network.response.banner

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @field:SerializedName("bannerOrder")
    val bannerOrder: Int,
    @field:SerializedName("createdDate")
    val createDate: String,
    @field:SerializedName("expiryDateTime")
    val expiryDateTime: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("image")
    val image: String,
    @field:SerializedName("redirectUrl")
    val redirectUrl: String,
    @field:SerializedName("status")
    val status: BannerStatusResponse,
    @field:SerializedName("title")
    val title: String
)
