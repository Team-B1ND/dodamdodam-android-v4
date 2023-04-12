package kr.hs.dgsw.smartschool.data.network.request.auth

import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

class ModifyPwRequest(
    newPw: String,
    pw: String,
    encryption: Boolean = true
) {
    var newPw: String? = null
    var pw: String? = null

    init {
        try {
            this.newPw = if (encryption) Utils.encryptSHA512(newPw) else newPw
            this.pw = if (encryption) Utils.encryptSHA512(pw) else pw
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
