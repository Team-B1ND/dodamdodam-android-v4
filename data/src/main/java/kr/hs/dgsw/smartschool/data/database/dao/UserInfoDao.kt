package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.UserInfoEntity

@Dao
interface UserInfoDao : BaseDao<UserInfoEntity> {
    @Query("SELECT * FROM userInfo_table")
    suspend fun getMyInfo(): UserInfoEntity

    @Query("DELETE FROM userInfo_table")
    suspend fun deleteUserInfo()
}
