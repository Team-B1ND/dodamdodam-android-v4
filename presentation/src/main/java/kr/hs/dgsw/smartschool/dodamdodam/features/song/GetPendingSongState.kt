package kr.hs.dgsw.smartschool.dodamdodam.features.song

import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData

data class GetPendingSongState(
    val songList: List<VideoYoutubeData> = emptyList(),
    val error: String = ""
)
