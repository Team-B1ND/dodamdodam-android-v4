package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class DefaultStudyRoom(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("place") var place: Place?,
    @field:SerializedName("student") val student: Student?,
    @field:SerializedName("timeTable") val timeTable: TimeTable
) {
    constructor(timetable: TimeTable, place: Place): this(
        null,
        place,
        null,
        timetable
    )

    constructor(timeTable: TimeTable): this(
        null,
        null,
        null,
        timeTable
    )

    constructor(timeTable: TimeTable, defaultStudyRoom: DefaultStudyRoom): this(
        defaultStudyRoom.id,
        defaultStudyRoom.place,
        defaultStudyRoom.student,
        timeTable
    )
}
