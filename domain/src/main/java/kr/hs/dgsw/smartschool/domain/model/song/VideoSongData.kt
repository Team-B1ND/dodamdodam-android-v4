package kr.hs.dgsw.smartschool.domain.model.song

data class VideoSongData(
    val source: Song?,
    val quality: String?,
) : SongData (
    source?.videoId,
    "https://i.ytimg.com/vi/" + source?.videoId + "/" + quality + ".jpg",
    source?.videoTitle.toString(),
    source?.channelTitle.toString(),
    source?.id ?: 0,
    source?.createdDate ?: "",
    source?.playDate
)
