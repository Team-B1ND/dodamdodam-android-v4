package kr.hs.dgsw.smartschool.domain.request

import kr.hs.dgsw.smartschool.domain.model.member.StudentInfo
import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

class SignUpRequest(
    id: String,
    pw: String,
    email: String,
    phone: String,
    name: String,
    additionalInfo: StudentInfo
) {
    var id: String? = null
    var pw: String? = null
    var email: String? = null
    var phone: String? = null
    var name: String? = null
    private var accountType: String? = null
    var additionalInfo: StudentInfo? = null

    init {
        try {
            this.id = id
            this.pw = Utils.encryptSHA512(pw)
            this.email = email
            this.phone = phone
            this.name = name
            this.accountType = "1"
            this.additionalInfo = additionalInfo
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
