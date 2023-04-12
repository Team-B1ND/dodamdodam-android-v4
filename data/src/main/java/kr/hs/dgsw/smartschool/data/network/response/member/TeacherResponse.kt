package kr.hs.dgsw.smartschool.data.network.response.member

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class TeacherResponse(
    val id: Int,
    val member: Member,
    val phone: String,
    val position: String,
    val tel: String
)
