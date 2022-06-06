package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity

@Dao
interface MealDao : BaseDao<MealEntity> {
    @Query("SELECT * FROM meal_table")
    suspend fun getAllMeal(): List<MealEntity>

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month")
    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity>

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month AND day=:day")
    suspend fun getMealByDay(year: Int, month: Int, day: Int): List<MealEntity>

    @Query("DELETE FROM meal_table")
    suspend fun deleteAll()
}