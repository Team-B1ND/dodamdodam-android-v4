package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_post_table")
data class CardPostEntity(
    @PrimaryKey(autoGenerate = true)
    val postId: Int = 0,
    val title: String,
    val text: String,
    val image: String,
    val link: String,
    val hashtag: String?,
    val isLinkPosting: Boolean
)
