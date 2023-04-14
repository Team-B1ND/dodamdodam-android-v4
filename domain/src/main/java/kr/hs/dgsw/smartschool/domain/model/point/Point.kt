package kr.hs.dgsw.smartschool.domain.model.point

import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher

data class Point(
    val reason: String,
    val idx: Int,
    val score: Int,
    val student: Student,
    val teacherIdx: Teacher,
    val type: PointType,
    val given_date: String,
    val target: PointPlace
)
