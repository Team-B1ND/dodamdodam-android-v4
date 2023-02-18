package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.TeacherDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val teacherDataSource: TeacherDataSource
) : TeacherRepository {

    override suspend fun getAllTeacher(): List<Teacher> =
        teacherDataSource.getAllTeacher().map { studentEntity -> studentEntity.toModel() }
}
