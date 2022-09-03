package kr.hs.dgsw.smartschool.dodamdodam.features.meal.state

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class GetMealState(
    val isUpdate: Boolean = false,
    val meal: Meal? = null,
    val error: String = ""
)
