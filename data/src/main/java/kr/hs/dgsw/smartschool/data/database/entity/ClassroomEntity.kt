package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classroom_table")
data class ClassroomEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "grade", defaultValue = "-1") val grade: Int,
    val room: Int,
    val placeId: Int,
    val placeName: String,
    val typeId: Int,
    val typeName: String
)
