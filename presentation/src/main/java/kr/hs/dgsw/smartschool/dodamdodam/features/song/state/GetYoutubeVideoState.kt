package kr.hs.dgsw.smartschool.dodamdodam.features.song.state

import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo

data class GetYoutubeVideoState(
    val youtubeVideo: YoutubeVideo? = null,
    val error: String = ""
)
