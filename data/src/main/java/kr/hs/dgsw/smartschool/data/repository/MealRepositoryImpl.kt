package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.MealDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor (
    private val mealDataSource: MealDataSource
) : MealRepository {

    override suspend fun getMeal(year: Int, month: Int, day: Int): Meal =
        mealDataSource.getMeal(year, month, day)

    override suspend fun getCalorieOfMeal(): String {
        return mealDataSource.getCalorieOfMeal()
    }

}
