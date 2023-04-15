package kr.hs.dgsw.smartschool.data.network.response.place

data class PlaceResponse(
    val id: Int,
    val name: String,
    val type: PlaceTypeResponse
) {

    override fun toString(): String {
        return name
    }
}
