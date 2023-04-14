package kr.hs.dgsw.smartschool.domain.model.studyroom

import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class DefaultStudyRoom(
    val id: Int?,
    var place: Place?,
    val student: Student?,
    val timeTable: TimeTable
) {
    constructor(timetable: TimeTable, place: Place) : this(
        null,
        place,
        null,
        timetable
    )

    constructor(timeTable: TimeTable) : this(
        null,
        null,
        null,
        timeTable
    )

    constructor(timeTable: TimeTable, defaultStudyRoom: DefaultStudyRoom) : this(
        defaultStudyRoom.id,
        defaultStudyRoom.place,
        defaultStudyRoom.student,
        timeTable
    )
}
