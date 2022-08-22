package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class TeacherEntity(
    @PrimaryKey
    val idx: Int,
    val phone: String,
    val id: String,
    val name: String,
    val email: String,
    val accessLevel: Int,
    val allowed: Int,
    val profileImage: String?,
    val role: String
)
