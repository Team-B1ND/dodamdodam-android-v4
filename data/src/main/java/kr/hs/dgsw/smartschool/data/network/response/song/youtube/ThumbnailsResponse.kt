package kr.hs.dgsw.smartschool.data.network.response.song.youtube

data class ThumbnailsResponse(
    val default: DefaultRespone,
    val high: HighResponse,
    val medium: MediumResponse
)
