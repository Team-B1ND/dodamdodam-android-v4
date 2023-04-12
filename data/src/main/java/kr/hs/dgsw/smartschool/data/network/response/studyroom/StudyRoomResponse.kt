package kr.hs.dgsw.smartschool.data.network.response.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.data.network.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.data.network.response.time.TimeTableResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class StudyRoomResponse(
    @SerializedName("date") val date: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("place") var place: PlaceResponse?,
    @SerializedName("status") val status: StudyRoomStatusResponse?,
    @SerializedName("student") val student: StudentIdResponse?,
    @SerializedName("teacher") val teacher: TeacherIdResponse?,
    @SerializedName("timeTable") val timeTable: TimeTableResponse?
) : Cloneable {

    data class StudentIdResponse(
        val id: Int
    )

    data class TeacherIdResponse(
        val id: Int
    )

    constructor(timetableResponse: TimeTableResponse, placeResponse: PlaceResponse) : this(
        null,
        null,
        placeResponse,
        null,
        null,
        null,
        timetableResponse
    )

    constructor(timetableResponse: TimeTableResponse) : this(
        null,
        null,
        null,
        null,
        null,
        null,
        timetableResponse
    )

    constructor(timetableResponse: TimeTableResponse, studyRoom: StudyRoomResponse) : this(
        studyRoom.date,
        studyRoom.id,
        studyRoom.place,
        studyRoom.status,
        studyRoom.student,
        studyRoom.teacher,
        timetableResponse
    )

    public override fun clone(): StudyRoomResponse {
        return super.clone() as StudyRoomResponse
    }

    fun isExpire(): Boolean {
        val start = timeTable?.startTime?.dropLast(3)
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = format.format(Date())
        return start!! < currentTime
    }
}
