package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_table")
data class TimeEntity(
    @PrimaryKey val idx: Int,
    val name: String,
    val type: Int,
    val startTime: String,
    val endTime: String
)