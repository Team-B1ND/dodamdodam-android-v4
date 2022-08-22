package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "member_table")
data class MemberEntity(
    @PrimaryKey
    val idx: Int,
    val phone: String,
    val id: String,
    val name: String,
    val email: String,
    val accessLevel: Int,
    val allowed: Int,
    val profileImage: String?
)
