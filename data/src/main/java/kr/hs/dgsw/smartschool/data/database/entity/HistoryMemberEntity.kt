package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_member_table")
data class HistoryMemberEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val profileImage: String?
)
