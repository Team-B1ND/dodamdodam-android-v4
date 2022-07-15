package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("0")
    val zero: Int,
    @SerializedName("1")
    val one: Int
)