package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class MealState(
    val isLoading: Boolean = false,
    val meal: List<Meal> = emptyList(),
    val error: String = ""
)
