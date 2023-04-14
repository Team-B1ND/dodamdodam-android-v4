package kr.hs.dgsw.smartschool.data.network.response.member

import kr.hs.dgsw.smartschool.domain.model.member.Student

data class ParentResponse(
    val id: Int,
    val phone: String,
    val student: Student
)
