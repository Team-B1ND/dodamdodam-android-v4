package kr.hs.dgsw.smartschool.domain.model.song

data class VideoYoutubeData(
    val source: Video?,
    val quality: String?
) : YoutubeData(
    source?.videoId,
    "https://i.ytimg.com/vi/" + source?.videoId + "/" + quality + ".jpg",
    source?.videoTitle.toString(),
    source?.channelTitle.toString()
)
