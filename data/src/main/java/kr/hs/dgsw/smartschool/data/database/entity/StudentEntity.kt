package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentEntity(
    @PrimaryKey
    val studentId: Int,
    val grade: Int,
    val id: Int,
    val placeId: Int,
    val room: Int,
    val number: Int,
    val phone: String,
    val email: String,
    val memberId: String,
    val joinDate: String,
    val name: String,
    val profileImage: String,
    val role: String,
    val status: String
)
