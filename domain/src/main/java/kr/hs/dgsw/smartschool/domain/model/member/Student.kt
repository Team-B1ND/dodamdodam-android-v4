package kr.hs.dgsw.smartschool.domain.model.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

class Student(
    val classroom: Classroom,
    @SerializedName("id")
    val studentId: Int,
    member: Member,
    val number: Int,
    val phone: String
): Member(member.email, member.id, member.joinDate, member.name, member.profileImage, member.role, member.status)
