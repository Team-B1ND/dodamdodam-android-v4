package kr.hs.dgsw.smartschool.data.network.response.song.youtube

data class YoutubeVideoResponse(
    val etag: String,
    val items: List<ItemResponse>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfoResponse,
    val regionCode: String
)
