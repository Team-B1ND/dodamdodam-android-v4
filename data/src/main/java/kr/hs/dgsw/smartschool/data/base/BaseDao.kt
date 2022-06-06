package kr.hs.dgsw.smartschool.data.base

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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
