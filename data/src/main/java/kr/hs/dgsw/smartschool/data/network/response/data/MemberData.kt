package kr.hs.dgsw.smartschool.data.network.response.data

import kr.hs.dgsw.smartschool.data.network.response.member.ParentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherResponse

data class MemberData(
    val students: List<StudentResponse>,
    val teachers: List<TeacherResponse>,
    val parents: List<ParentResponse>
)
