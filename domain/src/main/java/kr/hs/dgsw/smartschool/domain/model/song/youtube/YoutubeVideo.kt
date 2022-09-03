package kr.hs.dgsw.smartschool.domain.model.song.youtube

data class YoutubeVideo(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)
