package kr.hs.dgsw.smartschool.domain.request

import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo

data class LocationRequest(
    val locations: List<LocationInfo>
)
