package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.location.Location

data class DefaultLocationRequest(
    val day: Int,
    @SerializedName("defaultLocations")
    val defaultLocations: List<Location>
)
