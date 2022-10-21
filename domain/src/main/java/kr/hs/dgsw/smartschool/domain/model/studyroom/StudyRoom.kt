package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class StudyRoom(
    @SerializedName("date") val date: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("place") var place: Place?,
    @SerializedName("status") val status: StudyRoomStatus?,
    @SerializedName("student") val student: StudentId?,
    @SerializedName("teacher") val teacher: TeacherId?,
    @SerializedName("timeTable") val timeTable: TimeTable?
) : Cloneable {

    data class StudentId(
        val id: Int
    )

    data class TeacherId(
        val id: Int
    )

    constructor(timetable: TimeTable, place: Place) : this(
        null,
        null,
        place,
        null,
        null,
        null,
        timetable
    )

    constructor(timeTable: TimeTable) : this(
        null,
        null,
        null,
        null,
        null,
        null,
        timeTable
    )

    constructor(timeTable: TimeTable, studyRoom: StudyRoom) : this(
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

    fun isExpire(): Boolean {
        val start = timeTable?.startTime?.dropLast(3)
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = format.format(Date())
        return start!! < currentTime
    }
}
