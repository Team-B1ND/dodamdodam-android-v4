package kr.hs.dgsw.smartschool.domain.model.classinfo

import com.google.gson.annotations.SerializedName

data class ClassInfo(
    val idx: Int,
    val grade: Int,
    @SerializedName("placeIdx") val placeIdx: Int,
    val room: Int
)
