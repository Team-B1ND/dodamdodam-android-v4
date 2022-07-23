package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

data class YearScore(
    // 0 -> 기숙사
    // 1 -> 학교
    @SerializedName("0")
    val dormitoryPoint: Int,
    @SerializedName("1")
    val schoolPoint: Int
)