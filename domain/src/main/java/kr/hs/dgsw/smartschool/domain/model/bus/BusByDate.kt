package kr.hs.dgsw.smartschool.domain.model.bus

import com.google.gson.annotations.SerializedName

data class BusByDate(
    val date: String,
    @SerializedName("bus")
    val bustList: List<Bus>
)
