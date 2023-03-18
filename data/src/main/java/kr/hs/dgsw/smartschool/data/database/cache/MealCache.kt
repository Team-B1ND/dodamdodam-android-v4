package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.entity.CalorieEntity
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import javax.inject.Inject

class MealCache @Inject constructor(application: Application) : BaseCache(application) {
    private val mealDao = database.mealDao()
    private val calorieDao = database.calorieDao()

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

    suspend fun insertCalorieList(calorieList: List<CalorieEntity>) {
        calorieDao.insert(calorieList)
    }

    suspend fun getAllCalories(): List<CalorieEntity> =
        calorieDao.getAllCalories()

    suspend fun getCalorie(year: Int, month: Int, day: Int): CalorieEntity? =
        calorieDao.getCalorie(year, month, day)

    suspend fun getCalorieByMonth(year: Int, month: Int): List<CalorieEntity> {
        val mealEntityList = calorieDao.getAllCalories()
        return mealEntityList.ifEmpty { emptyList() }
    }

    suspend fun deleteAllCalorie() = calorieDao.deleteAll()
}
