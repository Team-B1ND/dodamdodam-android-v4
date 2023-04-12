package kr.hs.dgsw.smartschool.data.network.request.song

import com.google.gson.annotations.SerializedName

data class SongRequest(
    @SerializedName("videoUrl") val videoUrl: String
)
