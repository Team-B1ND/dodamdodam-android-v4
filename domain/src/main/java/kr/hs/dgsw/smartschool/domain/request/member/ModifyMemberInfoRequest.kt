package kr.hs.dgsw.smartschool.domain.request.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.util.Utils.encryptSHA512
import java.security.NoSuchAlgorithmException

data class ModifyMemberInfoRequest(
    var email: String?,
    var phone: String?,
    @SerializedName("imageUrl") var profileImage: Picture?,
    //TODO 비밀번호는 따로 APi 있음 수정 바람.
    var pw : String?
) {

    constructor(phone: String?, email: String?, profileImage: Picture?) : this(null, email, profileImage, null) {
        this.phone = phone
    }

    constructor(pw: String?) : this(null, null, null, null) {
        try {
            this.pw = encryptSHA512(pw!!)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
