package kr.hs.dgsw.smartschool.dodamdodam.features.meal.state

data class GetMealCalorieState(
    val isUpdate: Boolean = false,
    val calorie: String? = null,
    val error: String = ""
)
