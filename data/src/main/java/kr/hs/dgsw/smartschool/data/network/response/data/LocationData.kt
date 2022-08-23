package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.location.DefaultLocation
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo

data class LocationData(
    val locations: List<LocationInfo?>,
    @SerializedName("defaultLocations")
    val defaultLocations: List<DefaultLocation>
)
