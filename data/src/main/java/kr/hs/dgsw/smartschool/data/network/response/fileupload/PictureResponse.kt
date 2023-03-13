package kr.hs.dgsw.smartschool.data.network.response.fileupload

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PictureResponse(
    @SerializedName("originalName") val originalName: String?,
    @SerializedName("uploadName") var uploadName: String,
    val type: String,
    val url: String?
) : Serializable {

    constructor(originalName: String?, uploadName: String, type: String) : this(originalName, uploadName, type, null)

    companion object {
        fun toMemberPicture(pictureResponse: PictureResponse): PictureResponse? =
            if (pictureResponse == null) null else PictureResponse(null, pictureResponse.uploadName, pictureResponse.type)
    }
}
