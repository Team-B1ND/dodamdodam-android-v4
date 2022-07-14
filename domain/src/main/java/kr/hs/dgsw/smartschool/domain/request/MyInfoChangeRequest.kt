package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.util.Utils.encryptSHA512
import java.security.NoSuchAlgorithmException

data class MyInfoChangeRequest(
    var phone: String?,
    var email: String?,
    @SerializedName("profileImage")
    var profileImage: Picture?,
    var pw: String?
) {

    constructor(phone: String?, email: String?, profileImage: Picture?): this(null, email, profileImage, null) {
        this.phone = phone
    }

    constructor(pw: String?): this(null, null, null, null) {
        try {
            this.pw = encryptSHA512(pw!!)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
