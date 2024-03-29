package kr.hs.dgsw.smartschool.data.network.request.auth

import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

class LoginRequest(
    id: String,
    pw: String,
    encryption: Boolean = true
) {
    var id: String? = null
    var pw: String? = null

    init {
        try {
            this.id = id
            this.pw = if (encryption) Utils.encryptSHA512(pw) else pw
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
