package kr.hs.dgsw.smartschool.domain.model.song.youtube

data class Item(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)
