package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.MealDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.exception.NullMealException
import javax.inject.Inject
import kotlin.jvm.Throws

class MealCache @Inject constructor(application: Application) : BaseCache(application) {
    private val mealDao: MealDao = database.mealDao()

    suspend fun insertMealList(mealEntities: List<MealEntity>) = mealDao.insert(mealEntities)

    suspend fun deleteAllMeal() = mealDao.deleteAll()

    @Throws(NullMealException::class)
    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity> {
        val mealEntityList = mealDao.getMealByMonth(year, month)

        return if (mealDao.getMealByMonth(year, month).isEmpty()) {
            throw NullMealException("급식이 없습니다.")
        } else {
            mealEntityList
        }
    }
}
