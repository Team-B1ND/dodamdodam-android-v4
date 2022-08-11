package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class SongCheckRequest(
    @SerializedName("videoIdx") val videosIdx: List<Int>,
    @SerializedName("checkDate") val checkDate: Date
)
