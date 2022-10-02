package kr.hs.dgsw.smartschool.domain.model.point

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.StudentId
import kr.hs.dgsw.smartschool.domain.model.member.TeacherId

data class MyYearPoint(
    @field:SerializedName("given_date") val givenDate: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("point_reason") val pointReason: PointReason,
    @field:SerializedName("student") val student: StudentId,
    @field:SerializedName("teacher") val teacher: TeacherId
)
