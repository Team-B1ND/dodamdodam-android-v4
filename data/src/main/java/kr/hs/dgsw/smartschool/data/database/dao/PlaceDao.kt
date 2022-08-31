package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity

@Dao
interface PlaceDao : BaseDao<PlaceEntity> {
    @Query("SELECT * FROM place_table WHERE id=:idx")
    suspend fun getPlaceByIdx(idx: Int): PlaceEntity

    @Query("SELECT * FROM place_table")
    suspend fun getAllPlace(): List<PlaceEntity>

    @Query("DELETE FROM place_table")
    suspend fun deleteAll()
}
