package kr.hs.dgsw.smartschool.data.network.response.song

data class VideoSongDataResponse(
    val source: SongResponse,
    val quality: String?,
)
