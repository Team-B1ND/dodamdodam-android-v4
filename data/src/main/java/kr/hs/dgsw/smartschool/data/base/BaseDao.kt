package kr.hs.dgsw.smartschool.data.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ET)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ET>)

    @Update
    suspend fun update(entity: ET)

    @Delete
    suspend fun delete(entity: ET)
}
