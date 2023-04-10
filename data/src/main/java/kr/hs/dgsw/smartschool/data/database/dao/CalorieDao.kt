package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.CalorieEntity

@Dao
interface CalorieDao : BaseDao<CalorieEntity> {

    @Query("SELECT * FROM calorie_table WHERE year=:year AND month=:month AND day=:day")
    suspend fun getCalorie(year: Int, month: Int, day: Int): CalorieEntity?

    @Query("SELECT * FROM calorie_table WHERE year=:year AND month=:month")
    suspend fun getCalorieByMonth(year: Int, month: Int): List<CalorieEntity>

    @Query("SELECT * FROM calorie_table")
    suspend fun getAllCalories(): List<CalorieEntity>

    @Query("DELETE FROM calorie_table")
    suspend fun deleteAll()
}
