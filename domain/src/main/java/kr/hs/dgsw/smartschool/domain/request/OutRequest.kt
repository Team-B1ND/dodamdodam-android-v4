package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName

data class OutRequest(
    @field:SerializedName("passIdx")
    val outGoingIdx: Int?,
    @field:SerializedName("leaveIdx")
    val outSleepingIdx: Int?,
    @field:SerializedName("startTime")
    val startTime: Int?,
    @field:SerializedName("endTime")
    val endTime: Int?,
    @field:SerializedName("reason")
    val reason: Int?,
)
