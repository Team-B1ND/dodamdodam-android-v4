package kr.hs.dgsw.smartschool.data.network.response.song

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.data.network.response.member.MemberResponse

data class SongResponse(
    @field:SerializedName("applyingMember") val applyingMember: MemberResponse,
    @field:SerializedName("channelTitle") val channelTitle: String,
    @field:SerializedName("checkingMember") val checkingMember: MemberResponse,
    @field:SerializedName("createdDate") val createdDate: String,
    @field:SerializedName("duration") val duration: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("playDate") val playDate: String,
    @field:SerializedName("status") val status: SongStatusResponse,
    @field:SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @field:SerializedName("videoId") val videoId: String,
    @field:SerializedName("videoTitle") val videoTitle: String,
    @field:SerializedName("videoUrl") val videoUrl: String
)
