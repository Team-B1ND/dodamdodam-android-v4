package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class DefaultStudyRoom(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("place") val place: Place,
    @field:SerializedName("student") val student: Student,
    @field:SerializedName("timeTable") val timeTable: TimeTable
)
