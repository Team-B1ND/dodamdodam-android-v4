package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

class MyYearPoint(
    @SerializedName("score") val yearScore: YearScore,
    @SerializedName("log") val log: List<Log>
)
