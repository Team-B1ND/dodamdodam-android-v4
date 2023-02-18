package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.StudentDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.request.member.ModifyMemberInfoRequest
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDataSource: StudentDataSource
) : StudentRepository {

    override suspend fun getMyInfo(): Student =
        studentDataSource.getMyInfo()

    override suspend fun modifyMemberInfo(request: ModifyMemberInfoRequest): String =
        studentDataSource.modifyMemberInfo(request)

    override suspend fun getAllStudent(): List<Student> =
        studentDataSource.getAllStudent().map { studentEntity -> studentEntity.toModel() }

    override suspend fun getStudent(id: String): Student =
        studentDataSource.getStudent(id).toModel()
}
