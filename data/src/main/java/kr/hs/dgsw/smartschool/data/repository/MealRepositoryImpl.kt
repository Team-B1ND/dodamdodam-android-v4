package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.MealDataSource
import kr.hs.dgsw.smartschool.data.mapper.MealMapper
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor (
    private val mealDataSource: MealDataSource
) : MealRepository {
    private val mealMapper = MealMapper()

    override suspend fun getAllMeal(year: Int, month: Int): List<Meal> =
        mealDataSource.getAllMeal(year, month).map {
            mealMapper.mapToModel(it)
        }

    override suspend fun deleteAllMeal() =
        mealDataSource.deleteAllMeal()
}
