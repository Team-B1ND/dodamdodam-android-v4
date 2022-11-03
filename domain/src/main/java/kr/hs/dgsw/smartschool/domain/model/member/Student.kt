package kr.hs.dgsw.smartschool.domain.model.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

class Student(
    @field:SerializedName("classroom") val classroom: Classroom,
    @field:SerializedName("id") val studentId: Int,
    @field:SerializedName("member") val member: Member,
    @field:SerializedName("number") val number: Int,
    @field:SerializedName("phone") val phone: String
)
