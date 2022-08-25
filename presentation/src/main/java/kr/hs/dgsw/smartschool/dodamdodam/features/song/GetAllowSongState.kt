package kr.hs.dgsw.smartschool.dodamdodam.features.song

import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData

data class GetAllowSongState(
    val songList: List<VideoYoutubeData> = emptyList(),
    val error: String = ""
)
