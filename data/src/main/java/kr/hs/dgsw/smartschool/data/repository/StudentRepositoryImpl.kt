package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.StudentDataSource
import kr.hs.dgsw.smartschool.data.mapper.StudentMapper
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.request.ModifyMemberInfoRequest
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDataSource: StudentDataSource
) : StudentRepository {

    private val studentMapper = StudentMapper()

    override suspend fun getMyInfo(): Student =
        studentDataSource.getMyInfo()

    override suspend fun changeMemberInfo(request: ModifyMemberInfoRequest): String =
        studentDataSource.changeMemberInfo(request)

    override suspend fun getAllStudent(): List<Student> =
        studentDataSource.getAllStudent().map { studentEntity -> studentMapper.mapToModel(studentEntity) }

    override suspend fun getStudent(id: String): Student =
        studentMapper.mapToModel(studentDataSource.getStudent(id))
}
