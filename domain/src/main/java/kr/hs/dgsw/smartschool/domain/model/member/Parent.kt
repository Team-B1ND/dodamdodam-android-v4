package kr.hs.dgsw.smartschool.domain.model.member

import java.util.Date

class Parent(
    idx: Int,
    phone: String,
    id: String,
    name: String,
    email: String,
    accessLevel: Int,
    allowed: MemberAllowedType,
    joinDate: Date?,
    profileImage: String?,
    val studentIdx: Int,
    val relationShip: String
) : Member(idx, phone, id, name, email, accessLevel, allowed, joinDate, profileImage)
