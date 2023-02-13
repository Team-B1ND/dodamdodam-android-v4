package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity

@Dao
interface MealDao : BaseDao<MealEntity> {

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month AND day=:day")
    suspend fun getMeal(year: Int, month: Int, day: Int): MealEntity?

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month")
    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity>

    @Query("SELECT * FROM meal_table")
    suspend fun getAllMeal(): List<MealEntity>

    @Query("DELETE FROM meal_table")
    suspend fun deleteAll()
}
