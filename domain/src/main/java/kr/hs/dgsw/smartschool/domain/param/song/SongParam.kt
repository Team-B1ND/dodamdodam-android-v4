package kr.hs.dgsw.smartschool.domain.param.song

import com.google.gson.annotations.SerializedName

data class SongParam(
    @SerializedName("videoUrl") val videoUrl: String
)
