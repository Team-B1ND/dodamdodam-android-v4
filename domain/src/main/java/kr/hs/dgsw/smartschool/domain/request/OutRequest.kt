package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName

data class OutRequest(
    @field:SerializedName("passIdx")
    val outGoingIdx: Int?,
    @field:SerializedName("leaveIdx")
    val outSleepingIdx: Int?,
    @field:SerializedName("startTime")
    val startTime: String,
    @field:SerializedName("endTime")
    val endTime: String,
    @field:SerializedName("reason")
    val reason: String,
) {
    constructor(startTime: String, endTime: String, reason: String) : this(null, null, startTime, endTime, reason)
}
