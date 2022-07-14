package kr.hs.dgsw.smartschool.domain.model.member

import java.util.*

class Teacher(
    idx: Int,
    phone: String,
    id: String,
    name: String,
    email: String,
    accessLevel: Int,
    allowed: MemberAllowedType,
    joinDate: Date?,
    profileImage: String?,
    val role: String
) : Member(idx, phone, id, name, email, accessLevel, allowed, joinDate, profileImage)