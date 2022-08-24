package kr.hs.dgsw.smartschool.domain.model.member

import java.io.Serializable
import java.util.Date

open class Member(
    val email: String,
    val id: String,
    val joinDate: Date?,
    val name: String,
    val profileImage: String?,
    val role: String,
    val status: String,
    val phone: String,
) : Serializable
