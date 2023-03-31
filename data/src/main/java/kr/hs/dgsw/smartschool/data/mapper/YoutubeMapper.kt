package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.song.youtube.ItemResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.PageInfoResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.YoutubeVideoResponse
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Item
import kr.hs.dgsw.smartschool.domain.model.song.youtube.PageInfo
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo

fun YoutubeVideoResponse.toModel() = YoutubeVideo(
    etag = etag,
    items = items.map { it.toModel() },
    kind = kind,
    nextPageToken = nextPageToken,
    pageInfo = pageInfo.toModel(),
    regionCode = regionCode
)

fun ItemResponse.toModel() = Item(
    etag = etag,
    id = id,
    kind = kind,
    snippet = snippet
)

fun PageInfoResponse.toModel() = PageInfo(
    resultsPerPage = resultsPerPage,
    totalResults = totalResults
)
