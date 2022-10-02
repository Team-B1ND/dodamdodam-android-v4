package kr.hs.dgsw.smartschool.domain.request.song

import com.google.gson.annotations.SerializedName

data class SongRequest(
    @SerializedName("videoUrl") val videoUrl: String
)
