package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_table")
data class TimeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val startTime: String,
    val endTime: String
)
