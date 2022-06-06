package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import java.lang.Exception

@Entity(
    tableName = "meal_table",
    primaryKeys = ["year", "month", "day"])
data class MealEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val exists: Boolean,
    val breakfast: String?,
    val lunch: String?,
    val dinner: String?
)