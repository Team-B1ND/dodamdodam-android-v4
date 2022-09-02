package kr.hs.dgsw.smartschool.domain.model.song

import java.util.Locale

open class SongData(
    val videoId: String?,
    val thumbnailUrl: String,
    val videoTitle: String,
    val channelTitle: String,
    val id: Int,
    val createdDate: String,
    val playDate: String?
) {

    val lowerThumbnailUrl: String
        get() {
            val lowerQuality: String
            val splits = thumbnailUrl.split("/").toTypedArray()
            lowerQuality = when (splits[4]) {
                "hqdefault", "0" -> "mqdefault"
                "standard" -> "hqdefault"
                "maxresdefault" -> "standard"
                else -> "default"
            }
            return "https://i.ytimg.com/vi/$videoId/$lowerQuality.jpg"
        }

    val higherThumbnailUrl: String
        get() {
            val higherQuality: String
            val splits = thumbnailUrl.split("/").toTypedArray()
            higherQuality = when (splits[4]) {
                "default" -> "mqdefault"
                "mqdefault", "standard" -> "hqdefault"
                "hqdefault", "0" -> "sddefault"
                "sddefault" -> "maxresdefault"
                else -> "maxresdefault"
            }
            return "https://i.ytimg.com/vi/$videoId/$higherQuality.jpg"
        }

    val videoUrl: String
        get() = String.format(
            Locale.getDefault(),
            "https://www.youtube.com/watch?v=%s",
            videoId
        )

    companion object {
        private val qualities = arrayOf(
            "default",
            "mqdefault",
            "hqdefault",
            "sddefault",
            "maxresdefault",
            "0",
            "1",
            "2",
            "3"
        )
    }
}
