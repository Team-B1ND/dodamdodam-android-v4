package kr.hs.dgsw.smartschool.domain.request.song

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SongCheckRequest(
    @SerializedName("videoIdx") val videosIdx: List<Int>,
    @SerializedName("checkDate") val checkDate: Date
)
