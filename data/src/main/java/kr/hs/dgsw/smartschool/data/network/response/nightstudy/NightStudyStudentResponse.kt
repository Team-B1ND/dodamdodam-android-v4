package kr.hs.dgsw.smartschool.data.network.response.nightstudy

import com.google.gson.annotations.SerializedName

data class NightStudyStudentResponse(
    @SerializedName("grade")val grade: Int,
    @SerializedName("name")val name: String,
    @SerializedName("number")val number: Int,
    @SerializedName("room")val room: Int
)
