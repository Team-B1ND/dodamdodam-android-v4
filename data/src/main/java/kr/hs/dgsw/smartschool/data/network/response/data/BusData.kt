package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName

data class BusData<BusType>(
    @SerializedName("busList")
    val busList: List<BusType>
)