package kr.hs.dgsw.smartschool.domain.request.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.util.Utils.getUrlExtension
import kr.hs.dgsw.smartschool.domain.util.Utils.getUrlFileName

data class ModifyMemberInfoRequest(
    var email: String?,
    var phone: String?,
    @SerializedName("profileImage") var profileImage: ProfileImage?,
    var pw: String?
) {

    constructor(phone: String, email: String, url: String?) : this(
        email,
        phone,
        url?.let {
            ProfileImage(
                type = it.getUrlExtension(),
                //uploadName = "DODAM_FILE_${Random().nextInt(Int.MAX_VALUE)}"
                uploadName = it.getUrlFileName()
            )
        },
        null
    )

    data class ProfileImage(
        val type: String,
        val uploadName: String
    )
}
