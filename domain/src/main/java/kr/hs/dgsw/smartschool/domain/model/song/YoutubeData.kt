package kr.hs.dgsw.smartschool.domain.model.song

import java.util.*

open class YoutubeData(
    val videoId: String?,
    val thumbnailUrl: String,
    val videoTitle: String,
    val channelTitle: String
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