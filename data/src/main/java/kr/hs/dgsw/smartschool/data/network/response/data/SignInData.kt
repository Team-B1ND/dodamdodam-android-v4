package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class SignInData(
    val token: String,
    val refreshToken: String,
    val member: Member
)
