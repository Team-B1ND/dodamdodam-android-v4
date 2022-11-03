package kr.hs.dgsw.smartschool.domain.model.bus

import com.google.gson.annotations.SerializedName

data class BusByDate(
    @SerializedName("bus")
    val busList: List<Bus>,
    val date: String
)
