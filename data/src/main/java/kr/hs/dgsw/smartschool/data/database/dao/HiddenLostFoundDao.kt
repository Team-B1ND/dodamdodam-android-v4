package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.HiddenLostFoundEntity

@Dao
interface HiddenLostFoundDao : BaseDao<HiddenLostFoundEntity> {

    @Query("INSERT INTO hidden_lost_found_table(idx,memberId, title, place, content, contact) VALUES(:idx,:memberId,:title,:place,:content,:contact)")
    suspend fun insertHiddenLostFound(idx : Int, memberId : String, title : String, place : String, content : String, contact:String)

    @Query("SELECT * FROM hidden_lost_found_table")
    suspend fun getHiddenLostFoundList(): List<HiddenLostFoundEntity>

    @Query("DELETE FROM hidden_lost_found_table")
    suspend fun deleteHiddenLostFound()
}