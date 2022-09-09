package kr.hs.dgsw.smartschool.domain.request.auth

import kr.hs.dgsw.smartschool.domain.util.Utils
import java.security.NoSuchAlgorithmException

class JoinRequest(
    email: String,
    grade: Int,
    id: String,
    name: String,
    number: Int,
    phone: String,
    pw: String,
    room: Int
) {
    var email: String? = null
    var grade: Int? = null
    var id: String? = null
    var name: String? = null
    var number: Int? = null
    var phone: String? = null
    var pw: String? = null
    private var role: String? = null
    var room: Int? = null

    init {
        try {
            this.email = email
            this.grade = grade
            this.id = id
            this.name = name
            this.number = number
            this.phone = phone
            this.pw = Utils.encryptSHA512(pw)
            this.role = "STUDENT"
            this.room = room
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
