package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.HistoryMemberEntity

@Dao
interface HistoryMemberDao : BaseDao<HistoryMemberEntity> {

    @Query("SELECT * FROM history_member_table")
    suspend fun getAllHistoryMember(): List<HistoryMemberEntity>

    @Query("DELETE FROM history_member_table")
    suspend fun deleteAll()

}