package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

data class YearScore(
    // 0 -> 기숙사
    // 1 -> 학교
    @SerializedName("0")
    val zero: Int,
    @SerializedName("1")
    val one: Int
)