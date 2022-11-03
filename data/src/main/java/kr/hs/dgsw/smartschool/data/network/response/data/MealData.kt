package kr.hs.dgsw.smartschool.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class MealData(
    @SerializedName("meal")
    val meals: List<Meal>
)
