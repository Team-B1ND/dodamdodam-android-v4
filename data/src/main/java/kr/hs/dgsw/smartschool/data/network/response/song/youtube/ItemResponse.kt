package kr.hs.dgsw.smartschool.data.network.response.song.youtube

data class ItemResponse(
    val etag: String,
    val id: IdResponse,
    val kind: String,
    val snippet: SnippetResponse
)
