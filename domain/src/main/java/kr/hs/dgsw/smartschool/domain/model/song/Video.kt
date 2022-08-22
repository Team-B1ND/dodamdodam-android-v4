package kr.hs.dgsw.smartschool.domain.model.song

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Video(
    @SerializedName("idx") val idx: Int,
    @SerializedName("applyMemberId") val applyMemberId: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("videoTitle") val videoTitle: String,
    @SerializedName("videoId") val videoId: String,
    @SerializedName("videoUrl") val videoUrl: String,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("isAllow") val isAllow: Int,
    @SerializedName("checkMemberId") val checkMemberId: String?,
    @SerializedName("submitDate") val submitDate: Date?,
    @SerializedName("playDate") val playDate: Date?
) : Serializable
