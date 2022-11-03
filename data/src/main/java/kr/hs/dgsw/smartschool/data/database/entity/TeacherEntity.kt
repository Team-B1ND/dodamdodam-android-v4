package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class TeacherEntity(
    @PrimaryKey
    val teacherId: Int,
    val email: String,
    val memberId: String,
    val joinDate: String?,
    val name: String,
    val profileImage: String?,
    val role: String,
    val status: String,
    val phone: String,
    val position: String,
    val tel: String
)
