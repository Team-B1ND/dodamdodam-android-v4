package kr.hs.dgsw.smartschool.domain.model.fileupload

import java.io.Serializable

data class Picture(
    val originalName: String?,
    var uploadName: String,
    val type: String,
    val url: String?
) : Serializable {

    constructor(originalName: String?, uploadName: String, type: String) : this(originalName, uploadName, type, null)

    companion object {
        fun toMemberPicture(picture: Picture?): Picture? =
            if (picture == null) null else Picture(null, picture.uploadName, picture.type)
    }
}
