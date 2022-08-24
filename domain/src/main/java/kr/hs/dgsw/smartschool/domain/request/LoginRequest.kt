package kr.hs.dgsw.smartschool.domain.request

import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

class LoginRequest(
    id: String,
    pw: String
) {
    var id: String? = null
    var pw: String? = null

    init {
        try {
            this.id = id
            this.pw = Utils.encryptSHA512(pw)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
