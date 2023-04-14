package kr.hs.dgsw.smartschool.data.network.response.point

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherResponse

data class PointResponse(
    val reason: String,
    @SerializedName("id") val idx: Int,
    val score: Int,
    val student: StudentResponse,
    val teacher: TeacherResponse,
    val type: PointTypeResponse,
    val given_date: String,
    @SerializedName("place") val target: PointPlaceResponse
)
