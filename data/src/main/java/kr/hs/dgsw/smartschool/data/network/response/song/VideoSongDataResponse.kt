package kr.hs.dgsw.smartschool.data.network.response.song

import kr.hs.dgsw.smartschool.data.mapper.toModel

data class VideoSongDataResponse(
    val source: SongResponse,
    val quality: String?,
) : SongDataResponse(
    source.videoId,
    "https://i.ytimg.com/vi/" + source.videoId + "/" + quality + ".jpg",
    source.videoTitle,
    source.channelTitle,
    source.id,
    source.createdDate,
    source.playDate,
    source.applyingMember.toModel()
)
