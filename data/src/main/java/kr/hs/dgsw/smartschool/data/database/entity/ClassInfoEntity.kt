package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_info_table")
data class ClassInfoEntity(
    @PrimaryKey val idx: Int,
    @ColumnInfo(name = "grade", defaultValue = "-1") val grade: Int,
    val room: Int,
    val placeIdx: Int
)
