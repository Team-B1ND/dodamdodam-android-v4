package kr.hs.dgsw.smartschool.data.network.response.classroom

data class ClassroomPlaceResponse(
    val id: Int,
    val name: String,
    val type: ClassroomTypeResponse
)
