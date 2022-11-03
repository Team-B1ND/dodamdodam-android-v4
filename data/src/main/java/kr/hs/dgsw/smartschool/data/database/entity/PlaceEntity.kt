package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place_table")
data class PlaceEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val typeId: Int,
    val typeName: String
)
