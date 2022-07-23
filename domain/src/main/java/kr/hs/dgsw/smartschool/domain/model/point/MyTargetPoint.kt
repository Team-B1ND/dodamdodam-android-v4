package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName

class MyTargetPoint (
    @SerializedName("score") val targetScore: TargetScore,
    @SerializedName("log") val log: List<Log>
)