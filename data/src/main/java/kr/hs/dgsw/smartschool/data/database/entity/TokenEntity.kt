package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token_table")
data class TokenEntity(
    @PrimaryKey
    val idx: Int,
    val token: String,
    val refreshToken: String
) {
    constructor(token: String, refreshToken: String) : this(0, token, refreshToken)
}
