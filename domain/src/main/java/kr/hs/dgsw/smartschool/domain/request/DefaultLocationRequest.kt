package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom

data class DefaultLocationRequest(
    val day: Int,
    @SerializedName("defaultLocations")
    val defaultStudyRooms: List<DefaultStudyRoom>
)
