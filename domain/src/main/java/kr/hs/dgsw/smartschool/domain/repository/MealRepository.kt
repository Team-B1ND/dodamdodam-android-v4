package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

interface MealRepository {
    suspend fun getAllMeal(year: Int, month: Int): List<Meal>
    suspend fun deleteAllMeal()
}
