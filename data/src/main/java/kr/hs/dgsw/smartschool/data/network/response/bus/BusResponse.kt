package kr.hs.dgsw.smartschool.data.network.response.bus

import com.google.gson.annotations.SerializedName

data class BusResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("busName")
    val busName: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("peopleLimit")
    val peopleLimit: Int,
    @field:SerializedName("leaveTime")
    val leaveTime: String,
    @field:SerializedName("timeRequired")
    val timeRequired: String,
    @field:SerializedName("applyCount")
    val peopleCount: Int
)
