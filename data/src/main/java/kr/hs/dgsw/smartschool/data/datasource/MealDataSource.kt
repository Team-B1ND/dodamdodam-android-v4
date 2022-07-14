package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MealCache
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.exception.NullMealException
import kr.hs.dgsw.smartschool.data.mapper.MealMapper
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import javax.inject.Inject

class MealDataSource @Inject constructor(
    override val remote: MealRemote,
    override val cache: MealCache
) : BaseDataSource<MealRemote, Any>() {
    private val mealMapper = MealMapper()

    suspend fun getAllMeal(year: Int, month: Int): List<MealEntity> =
        try {
            cache.getMealByMonth(year, month)
        } catch (e: NullMealException) {
            getAllMealRemote(year, month)
        }

    private suspend fun getAllMealRemote(year: Int, month: Int): List<MealEntity> {
        val mealEntityList = remote.getAllMeals(year, month).data.meals.map { meal ->
            mealMapper.mapToEntity(meal)
        }
        cache.insertMealList(mealEntityList)
        return mealEntityList
    }

    suspend fun deleteAllMeal() = cache.deleteAllMeal()
}