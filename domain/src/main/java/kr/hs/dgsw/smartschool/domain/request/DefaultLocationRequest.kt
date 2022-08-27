package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName

data class DefaultLocationRequest(
    val day: Int,
    @SerializedName("defaultLocations")
    val defaultLocations: List<Location>
)
