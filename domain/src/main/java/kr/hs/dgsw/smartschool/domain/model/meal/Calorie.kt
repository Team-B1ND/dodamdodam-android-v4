package kr.hs.dgsw.smartschool.domain.model.meal

import com.google.gson.annotations.SerializedName

data class Calorie(
    @field:SerializedName("breakfast")
    val breakfast: String?,
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("dinner")
    val dinner: String?,
    @field:SerializedName("exists")
    val exists: Boolean,
    @field:SerializedName("lunch")
    val lunch: String?,
)
