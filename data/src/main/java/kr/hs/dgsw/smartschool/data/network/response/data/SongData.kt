package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.domain.model.song.Video

data class SongData(
    val allow: List<Video>?,
    val pending: List<Video>?,
    val videos: List<Video>?
)
