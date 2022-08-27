package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class LoginData(
    val member: Member,
    val refreshToken: String,
    val token: String
)
