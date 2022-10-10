package kr.hs.dgsw.smartschool.domain.model.bus

import com.google.gson.annotations.SerializedName

data class Bus(
    val id: Int,
    @SerializedName("busName")
    val busName: String,

    val description: String,

    @SerializedName("peopleLimit")
    val peopleLimit: Int,

    @SerializedName("leaveTime")
    val leaveTime: String,

    @SerializedName("timeRequired")
    val timeRequired: String,

    @SerializedName("busMemberlength")
    val busMemberLength: Int
)
