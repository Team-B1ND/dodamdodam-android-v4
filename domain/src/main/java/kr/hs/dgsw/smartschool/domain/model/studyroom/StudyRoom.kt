package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class StudyRoom(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("place") var place: Place?,
    @field:SerializedName("status") val status: String?,
    @field:SerializedName("student") val student: Student?,
    @field:SerializedName("teacher") val teacher: Teacher?,
    @field:SerializedName("time_table") val timeTable: TimeTable
): Cloneable {

    constructor(timetable: TimeTable, place: Place): this(
        null,
        null,
        place,
        null,
        null,
        null,
        timetable
    )

    constructor(timeTable: TimeTable): this(
        null,
        null,
        null,
        null,
        null,
        null,
        timeTable
    )

    constructor(timeTable: TimeTable, studyRoom: StudyRoom): this(
        studyRoom.date,
        studyRoom.id,
        studyRoom.place,
        studyRoom.status,
        studyRoom.student,
        studyRoom.teacher,
        timeTable
    )

    public override fun clone(): StudyRoom {
        return super.clone() as StudyRoom
    }

}
