package kr.hs.dgsw.b1nd.dodamdodam.domain.model.lostfound

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Picture(
    @SerializedName("originalName")
    val originalName: String?,
    @SerializedName("uploadName")
    val uploadName: String,
    val type: String,
    val url: String?
) : Serializable {

    constructor(originalName: String?, uploadName: String, type: String): this(originalName, uploadName, type, null)

    companion object {
        fun toMemberPicture(picture: Picture?): Picture? =
            if (picture == null) null else Picture(null, picture.uploadName, picture.type)
    }
}