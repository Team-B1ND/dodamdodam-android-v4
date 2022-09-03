package kr.hs.dgsw.smartschool.domain.model.song

data class VideoSongData(
    val source: Song,
    val quality: String?,
) : SongData (
    source.videoId,
    "https://i.ytimg.com/vi/" + source.videoId + "/" + quality + ".jpg",
    source.videoTitle,
    source.channelTitle,
    source.id,
    source.createdDate,
    source.playDate,
    source.applyingMember
)
