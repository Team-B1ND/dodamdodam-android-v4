package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.song.youtube.DefaultRespone
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.HighResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.IdResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.ItemResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.MediumResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.PageInfoResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.SnippetResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.ThumbnailsResponse
import kr.hs.dgsw.smartschool.data.network.response.song.youtube.YoutubeVideoResponse
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Default
import kr.hs.dgsw.smartschool.domain.model.song.youtube.High
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Id
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Item
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Medium
import kr.hs.dgsw.smartschool.domain.model.song.youtube.PageInfo
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Snippet
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Thumbnails
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
    id = id.toModel(),
    kind = kind,
    snippet = snippet.toModel()
)

fun SnippetResponse.toModel() = Snippet(
    channelId = channelId,
    channelTitle = channelTitle,
    description = description,
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime,
    publishedAt = publishedAt,
    thumbnails = thumbnails.toModel(),
    title = title
)

fun ThumbnailsResponse.toModel() = Thumbnails(
    default = default.toModel(),
    high = high.toModel(),
    medium = medium.toModel()
)

fun HighResponse.toModel() = High(
    height = height,
    url = url,
    width = width
)

fun MediumResponse.toModel() = Medium(
    height = height,
    url = url,
    width = width
)

fun DefaultRespone.toModel() = Default(
    height = height,
    url = url,
    width = width
)

fun IdResponse.toModel() = Id(
    kind = kind,
    videoId = videoId
)

fun PageInfoResponse.toModel() = PageInfo(
    resultsPerPage = resultsPerPage,
    totalResults = totalResults
)
