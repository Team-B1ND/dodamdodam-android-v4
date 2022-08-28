package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.domain.model.time.WeekType

@Entity(tableName = "time_table")
data class TimeEntity(
    @PrimaryKey val idx: Int,
    val name: String,
    val type: String,
    val startTime: String,
    val endTime: String
)
