package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity

@Entity(
    tableName = "sign_in_table",
    primaryKeys = ["token"]
)
data class SignInEntity(
    val token: String,
    val id: String,
    val pw: String
)
