package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class StudyRoom(
    @field:SerializedName("date") val date: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("place") val place: Place,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("student") val student: Student,
    @field:SerializedName("teacher") val teacher: Teacher,
    @field:SerializedName("time_table") val timeTable: TimeTable
): Cloneable {

    public override fun clone(): StudyRoom {
        return super.clone() as StudyRoom
    }

}
