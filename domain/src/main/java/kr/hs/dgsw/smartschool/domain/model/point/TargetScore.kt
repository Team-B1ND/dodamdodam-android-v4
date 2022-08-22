package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

data class TargetScore(
    @SerializedName("1")
    val bonusPoint: Int,
    @SerializedName("2")
    val minusPoint: Int,
    @SerializedName("3")
    val offsetPoint: Int
)
