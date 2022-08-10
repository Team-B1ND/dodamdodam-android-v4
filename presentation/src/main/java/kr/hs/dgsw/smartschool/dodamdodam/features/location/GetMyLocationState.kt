package kr.hs.dgsw.smartschool.dodamdodam.features.location

import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo

data class GetMyLocationState(
    val myLocations: List<LocationInfo> = emptyList(),
    val error: String = ""
)
