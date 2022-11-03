package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey
    val idx: Int,
    val id: String,
    val pw: String
) {
    constructor(id: String, pw: String) : this(0, id, pw)
}
