package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

interface MealRepository {

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal

    suspend fun getCalorieOfMeal(): String
}
