package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo_table")
data class UserInfoEntity(
    val id: String,
    @PrimaryKey
    val generation: Int,
    val name: String,
    val email: String,
    val role: String,
    val status: Int,
    val profileImage: String
)
