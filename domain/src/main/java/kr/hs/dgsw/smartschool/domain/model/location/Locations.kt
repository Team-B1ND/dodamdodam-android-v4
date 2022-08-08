package kr.hs.dgsw.smartschool.domain.model.location

data class Locations(
    val studentIdx: Int,
    val classIdx: Int,
    val locations: ArrayList<LocationInfo>
)
