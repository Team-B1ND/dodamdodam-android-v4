package kr.hs.dgsw.smartschool.data.network.response.classroom

import com.google.gson.annotations.SerializedName

data class ClassroomResponse(
    val id: Int,
    val grade: Int,
    @SerializedName("place") val place: ClassroomPlaceResponse,
    val room: Int
)
