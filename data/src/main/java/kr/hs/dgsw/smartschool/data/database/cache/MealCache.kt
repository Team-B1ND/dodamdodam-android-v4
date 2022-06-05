package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.MealDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import javax.inject.Inject

class MealCache @Inject constructor(application: Application): BaseCache(application) {
    private val mealDao: MealDao = database.mealDao()

    suspend fun insertMealList(mealEntities: List<MealEntity>) = mealDao.insert(mealEntities)

    suspend fun deleteAllMeal() = mealDao.deleteAll()

    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity> {
        Log.d("MealProcess", "MealCache: mealDao의 함수 실행")

        val mealEntityList = mealDao.getMealByMonth(year, month)

        return if (mealDao.getMealByMonth(year, month).isEmpty()) {
            emptyList()
        } else {
            mealEntityList
        }
    }
}