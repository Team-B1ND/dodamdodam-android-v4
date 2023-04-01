package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.song.SongRequest
import kr.hs.dgsw.smartschool.data.network.response.song.SongResponse
import kr.hs.dgsw.smartschool.data.network.response.song.SongStatusResponse
import kr.hs.dgsw.smartschool.data.network.response.song.VideoSongDataResponse
import kr.hs.dgsw.smartschool.data.network.response.song.melon.SongChartResponse
import kr.hs.dgsw.smartschool.domain.model.song.Song
import kr.hs.dgsw.smartschool.domain.model.song.SongStatus
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart
import kr.hs.dgsw.smartschool.domain.param.song.SongParam

fun SongChartResponse.toModel(): SongChart {
    return SongChart(
        name = name,
        album = album,
        artist = artist,
        thumbnail = thumbnail,
        rank = rank
    )
}

fun SongStatusResponse.toModel(): SongStatus = when (this.name) {
    SongStatus.ALLOWED.name -> SongStatus.ALLOWED
    SongStatus.DENIED.name -> SongStatus.DENIED
    else -> SongStatus.PENDING
}

fun SongResponse.toModel(): Song {
    return Song(
        applyingMember = applyingMember.toModel(),
        channelTitle = channelTitle,
        checkingMember = checkingMember.toModel(),
        createdDate = createdDate,
        duration = duration,
        id = id,
        playDate = playDate,
        status = status.toModel(),
        thumbnailUrl = thumbnailUrl,
        videoId = videoId,
        videoTitle = videoTitle,
        videoUrl = videoUrl
    )
}

fun VideoSongDataResponse.toModel(): VideoSongData {
    return VideoSongData(
        source = source.toModel(),
        quality = quality
    )
}

fun SongParam.toRequest(): SongRequest = SongRequest(
    videoUrl = videoUrl
)
