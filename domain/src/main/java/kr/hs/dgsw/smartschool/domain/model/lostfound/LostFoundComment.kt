package kr.hs.dgsw.smartschool.domain.model.lostfound

import android.net.Uri
import java.io.Serializable
import java.util.*

data class LostFoundComment(
    val idx: Int,
    val memberId: String,
    val type: LostFoundType,
    val title: String,
    val uploadTime: Date,
    val modifyTime: String,
    val place: String?,
    val content: String,
    val name: String?,
    val profileImage: String,
    val picture: List<Picture>?,

    var isMine: Boolean
) : Serializable {

    fun getPictureUrl(): List<String>? {
        return picture?.map { it.url!! }
    }

    fun getPictureUri(): List<Uri>? {
        return getPictureUrl()?.map { Uri.parse(it) }
    }
}
