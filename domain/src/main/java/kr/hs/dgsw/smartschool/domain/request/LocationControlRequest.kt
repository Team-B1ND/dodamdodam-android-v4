package kr.hs.dgsw.smartschool.domain.request

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.member.Student

data class LocationControlRequest(
    @SerializedName("studentIdx")
    var studentId: Int,
    var locations: List<LocationInfo>
) {
    constructor(student: Student, locations: List<LocationInfo>) : this(student.studentId, locations)
}
