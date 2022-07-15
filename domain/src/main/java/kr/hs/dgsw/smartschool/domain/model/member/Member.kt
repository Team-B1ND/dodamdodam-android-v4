package kr.hs.dgsw.smartschool.domain.model.member

import java.io.Serializable
import java.util.*

open class Member(
    val idx: Int,
    val phone: String,
    val id: String,
    val name: String,
    val email: String,
    val accessLevel: Int,
    val allowed: MemberAllowedType,
    val joinDate: Date?,
    val profileImage: String?
) : Serializable
