package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.MealDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
) : MealRepository {

    override suspend fun getMeal(year: Int, month: Int, day: Int): Meal =
        mealDataSource.getMeal(year, month, day)

    override suspend fun getCalorieOfMeal(year: Int, month: Int, day: Int): String? {
        val calorie = mealDataSource.getCalorieOfMeal(year, month, day)
        val breakfast = calorie.breakfast?.split(' ')?.first()?.toDouble() ?: 0.0
        val lunch = calorie.lunch?.split(' ')?.first()?.toDouble() ?: 0.0
        val dinner = calorie.dinner?.split(' ')?.first()?.toDouble() ?: 0.0

        if (breakfast + lunch + dinner == 0.0)
            return null
        return String.format("%.1f", breakfast + lunch + dinner)
    }
}
