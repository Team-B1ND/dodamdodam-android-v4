package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.member.Parent
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher

data class MemberData(
    val students: List<Student>,
    val teachers: List<Teacher>,
    val parents: List<Parent>
)
