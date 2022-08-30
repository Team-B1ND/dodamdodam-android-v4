package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity

@Dao
interface TimeDao : BaseDao<TimeEntity> {

    @Query("SELECT * FROM time_table WHERE type='WEEKDAY'")
    suspend fun getAllWeekdayTime(): List<TimeEntity>

    @Query("SELECT * FROM time_table WHERE type='WEEKEND'")
    suspend fun getAllWeekendTime(): List<TimeEntity>

    @Query("SELECT * FROM time_table WHERE id=:idx")
    suspend fun getTime(idx: Int): TimeEntity

    @Query("SELECT * FROM time_table")
    suspend fun getAllTime(): List<TimeEntity>

    @Query("DELETE FROM time_table")
    suspend fun deleteAll()
}
