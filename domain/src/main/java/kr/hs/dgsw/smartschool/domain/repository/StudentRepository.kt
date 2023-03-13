package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.param.member.ModifyMemberInfoRequest

interface StudentRepository {
    suspend fun getMyInfo(): Student
    suspend fun modifyMemberInfo(request: ModifyMemberInfoRequest): String
    suspend fun getAllStudent(): List<Student>
    suspend fun getStudent(id: String): Student
}
