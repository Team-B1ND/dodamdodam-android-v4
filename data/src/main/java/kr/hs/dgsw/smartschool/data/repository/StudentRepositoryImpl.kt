package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.StudentDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.request.member.ModifyMemberInfoRequest
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDataSource: StudentDataSource
) : StudentRepository {

    override suspend fun getMyInfo(): Student =
        studentDataSource.getMyInfo().toModel()

    override suspend fun modifyMemberInfo(email: String, imageUrl: String, phone: String,): String =
        studentDataSource.modifyMemberInfo(ModifyMemberInfoRequest(email, imageUrl, phone))

    override suspend fun getAllStudent(): List<Student> =
        studentDataSource.getAllStudent().map { studentEntity -> studentEntity.toModel() }

    override suspend fun getStudent(id: String): Student =
        studentDataSource.getStudent(id).toModel()
}
