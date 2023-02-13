package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import javax.inject.Inject

class MealCache @Inject constructor(application: Application) : BaseCache(application) {
    private val mealDao = database.mealDao()

    suspend fun insertMealList(mealList: List<MealEntity>) {
        mealDao.insert(mealList)
    }

    suspend fun getAllMeal(): List<MealEntity> =
        mealDao.getAllMeal()

    suspend fun getMeal(year: Int, month: Int, day: Int): MealEntity? =
        mealDao.getMeal(year, month, day)

    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity> {
        val mealEntityList = mealDao.getAllMeal()
        return mealEntityList.ifEmpty { emptyList() }
    }

    suspend fun deleteAll() = mealDao.deleteAll()
}
