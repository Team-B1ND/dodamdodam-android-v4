package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo

interface YouTubeRepository {

    suspend fun getYoutubeVideo(content: String): YoutubeVideo

}