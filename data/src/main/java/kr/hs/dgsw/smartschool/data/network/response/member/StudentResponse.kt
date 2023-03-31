package kr.hs.dgsw.smartschool.data.network.response.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

data class StudentResponse(
    @field:SerializedName("classroom") val classroom: Classroom,
    @field:SerializedName("id") val studentId: Int,
    @field:SerializedName("member") val member: MemberResponse,
    @field:SerializedName("number") val number: Int,
    @field:SerializedName("phone") val phone: String
)
