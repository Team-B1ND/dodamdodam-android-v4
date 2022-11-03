package kr.hs.dgsw.smartschool.dodamdodam.features.song.state

import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

data class GetPendingSongState(
    val songList: List<VideoSongData> = emptyList(),
    val error: String = ""
)
