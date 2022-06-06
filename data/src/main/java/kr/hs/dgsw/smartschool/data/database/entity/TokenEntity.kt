package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity

@Entity(
    tableName = "token_table",
    primaryKeys = ["index"]
)
data class TokenEntity(
    val index :Int,
    val token:String,
    val refreshToken:String
)

