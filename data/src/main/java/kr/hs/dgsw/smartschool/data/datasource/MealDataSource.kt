package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MealCache
import kr.hs.dgsw.smartschool.data.mapper.MealMapper
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import javax.inject.Inject

class MealDataSource @Inject constructor(
    override val remote: MealRemote,
    override val cache: MealCache
) : BaseDataSource<MealRemote, MealCache> {

    private val mealMapper = MealMapper()

    suspend fun getMeal(date: String): Meal {
        val year = date.split('-')[0].toInt()
        val month = date.split('-')[1].toInt()
        val day = date.split('-')[2].toInt()

        return cache.getMeal(year, month, day)?.toModel()
            ?: getRemoteMealList(year, month).find { it.date == date }
            ?: Meal(null, date, null, false, null)
    }

    private suspend fun getRemoteMealList(year: Int, month: Int): List<Meal> =
        remote.getMealOfMonth(month, year).also {
            cache.insertMealList(it.map { meal -> mealMapper.mapToEntity(meal) })
        }

    suspend fun getCalorieOfMeal(): String? =
        remote.getCalorieOfMeal()
}
