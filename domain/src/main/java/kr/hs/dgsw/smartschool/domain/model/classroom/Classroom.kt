package kr.hs.dgsw.smartschool.domain.model.classroom

import com.google.gson.annotations.SerializedName

data class Classroom(
    val id: Int,
    val grade: Int,
    @SerializedName("placeId") val placeId: Int,
    val room: Int
)
