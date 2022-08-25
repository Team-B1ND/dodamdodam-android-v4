package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.request.MyInfoChangeRequest

interface StudentRepository {
    suspend fun getMyInfo(): Student
    suspend fun changeMemberInfo(memberId: String, request: MyInfoChangeRequest)
    suspend fun getAllStudent(): List<Student>
    suspend fun getStudent(id: String): Student
}
