package kr.hs.dgsw.smartschool.domain.model.member

import java.io.Serializable

open class Member(
    val email: String,
    val id: String,
    val joinDate: String?,
    val name: String,
    val profileImage: String?,
    val role: String,
    val status: String
) : Serializable
