package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Student

interface StudentRepository {
    suspend fun getMyInfo(): Student
    suspend fun modifyMemberInfo(email: String, imageUrl: String, phone: String): String
    suspend fun getAllStudent(): List<Student>
    suspend fun getStudent(id: String): Student
}
