package kr.hs.dgsw.smartschool.domain.param.auth

import kr.hs.dgsw.smartschool.domain.util.Utils

data class JoinParam(
    val email: String,
    val grade: Int,
    val id: String,
    val name: String,
    val number: Int,
    val phone: String,
    val pw: String,
    val role: String = "STUDENT",
    val room: Int,
) {
    val encryptedPw: String
        get() = Utils.encryptSHA512(pw)
}
