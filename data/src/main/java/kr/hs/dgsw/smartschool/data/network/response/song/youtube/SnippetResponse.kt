package kr.hs.dgsw.smartschool.data.network.response.song.youtube

data class SnippetResponse(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsResponse,
    val title: String
)
