package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import kr.hs.dgsw.smartschool.domain.model.response.UserData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest

@Dao
interface SignInDao : BaseDao<TokenEntity> {
    @Query("INSERT INTO sign_in_table(token,id,pw) values(:token,:id,:pw)")
    suspend fun insertData(token: String,id : String,pw : String)

    @Query("SELECT userData(id,pw) FROM sign_in_table WHERE token = :token")
    suspend fun getData(token:String): SignInRequest

    @Query("DELETE FROM sign_in_table")
    suspend fun deleteAll()
}