package kr.hs.dgsw.smartschool.data.network.response.bus

import com.google.gson.annotations.SerializedName

data class BusByDateResponse(
    @field:SerializedName("bus")
    val busList: List<BusResponse>,
    @field:SerializedName("date")
    val date: String
)
