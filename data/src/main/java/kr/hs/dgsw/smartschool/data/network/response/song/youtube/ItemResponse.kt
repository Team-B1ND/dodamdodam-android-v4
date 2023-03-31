package kr.hs.dgsw.smartschool.data.network.response.song.youtube

import kr.hs.dgsw.smartschool.domain.model.song.youtube.Id
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Snippet

data class ItemResponse(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)
