package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity

@Dao
interface TokenDao : BaseDao<TokenEntity> {

    @Query("INSERT INTO token_table values(:index,:token,:refreshToken)")
    suspend fun insertToken(index:Int,token: String, refreshToken:String)

    @Query("SELECT token FROM token_table")
    suspend fun getToken(): String

    @Query("SELECT refreshToken FROM token_table")
    suspend fun getRefreshToken(): String

    @Query("Update token_table SET token = :token")
    suspend fun  updateToken(token: String)

    @Query("Update token_table SET refreshToken = :refreshToken")
    suspend fun updateRefreshToken(refreshToken: String)

    @Query("DELETE FROM token_table")
    suspend fun deleteAll()
}