package kr.hs.dgsw.smartschool.domain.model.member

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

data class Student(
    val classroom: Classroom,
    val studentId: Int,
    val member: Member,
    val number: Int,
    val phone: String
)
