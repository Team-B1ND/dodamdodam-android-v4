package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.CardPostEntity
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity

@Dao
interface CardPostDao : BaseDao<CardPostEntity> {
    @Query("SELECT * FROM card_post_table")
    suspend fun getMyCards(): List<CardPostEntity>

    @Query("SELECT * FROM card_post_table WHERE postId=:postId")
    suspend fun getMyCard(postId: Int): CardPostEntity

    @Query("DELETE FROM card_post_table WHERE postId=:postId")
    suspend fun deleteMyPost(postId: Int)
}