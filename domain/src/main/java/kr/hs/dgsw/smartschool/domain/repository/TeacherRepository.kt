package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Teacher

interface TeacherRepository {
    suspend fun getAllTeacher(): List<Teacher>
}
