package kr.hs.dgsw.smartschool.domain.model.song

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class Song(
    val applyingMember: Member,
    val channelTitle: String,
    val checkingMember: Member,
    val createdDate: String,
    val duration: String,
    val id: Int,
    val playDate: String,
    val status: SongStatus,
    val thumbnailUrl: String,
    val videoId: String,
    val videoTitle: String,
    val videoUrl: String
)
