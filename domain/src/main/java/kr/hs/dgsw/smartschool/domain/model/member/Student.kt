package kr.hs.dgsw.smartschool.domain.model.member

import java.util.*

class Student(
    idx: Int,
    phone: String,
    id: String,
    name: String,
    val classroomIdx: Int,
    val number: Int,
    email: String,
    accessLevel: Int,
    allowed: MemberAllowedType,
    joinDate: Date?,
    profileImage: String?,
    val grade: Int,
    val room: Int,
    val placeIdx: Int
) : Member(idx, phone, id, name, email, accessLevel, allowed, joinDate, profileImage)