package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hidden_lost_found_table")
data class HiddenLostFoundEntity(
    @PrimaryKey
    val idx: Int,
    val memberId: String,
    val title: String,
    val place: String?,
    val content: String,
    val contact: String?
)