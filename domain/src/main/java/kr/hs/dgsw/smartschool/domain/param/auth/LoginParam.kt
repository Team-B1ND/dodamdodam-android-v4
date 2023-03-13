package kr.hs.dgsw.smartschool.domain.param.auth

import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

data class LoginParam(
    val id: String,
    val pw: String,
    val encryption: Boolean = true
) {
    val encryptedPw: String
        get() = if (encryption) Utils.encryptSHA512(pw) else pw
}