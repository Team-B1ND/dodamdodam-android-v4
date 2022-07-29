package kr.hs.dgsw.smartschool.dodamdodam.features.location

import kr.hs.dgsw.smartschool.domain.model.place.Place

data class GetPlaceState(
    val place: List<Place> = emptyList(),
    val error: String = ""
)
