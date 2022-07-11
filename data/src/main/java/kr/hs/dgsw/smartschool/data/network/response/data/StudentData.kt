package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.member.Student

data class StudentData (
    val students: List<Student>
)