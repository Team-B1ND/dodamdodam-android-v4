package kr.hs.dgsw.smartschool.data.network.response.data

data class SongData(
    val allow: List<Video>?,
    val pending: List<Video>?,
    val videos: List<Video>?
)
