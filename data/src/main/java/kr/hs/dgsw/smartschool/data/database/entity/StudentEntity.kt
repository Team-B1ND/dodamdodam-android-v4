package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "student_table")
data class StudentEntity(
    @PrimaryKey
    val idx: Int,
    val phone: String,
    val id: String,
    val name: String,
    val classroomIdx: Int,
    val number: Int,
    val email: String,
    val accessLevel: Int,
    val allowed: Int,
    val profileImage: String?,
    val grade: Int,
    val room: Int,
    val placeIdx: Int
)