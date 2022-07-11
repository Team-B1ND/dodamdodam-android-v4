package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MemberCache
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.data.mapper.StudentMapper
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.request.MyInfoChangeRequest
import javax.inject.Inject

class StudentDataSource @Inject constructor(
    override val remote: MemberRemote,
    override val cache: MemberCache
) : BaseDataSource<MemberRemote, MemberCache>() {

    private val studentMapper = StudentMapper()

    suspend fun getMyInfo(): Student =
        remote.getMyInfo().memberData

    suspend fun changeMemberInfo(memberId: String, request: MyInfoChangeRequest) =
        remote.changeMemberInfo(memberId, request)

    suspend fun getStudent(id: String): StudentEntity = cache.getStudent(id)

    suspend fun getAllStudent(): List<StudentEntity> =
        cache.getAllStudent().ifEmpty { getAllStudentRemote() }

    suspend fun getAllStudentRemote(): List<StudentEntity> =
        remote.getAllStudent().students
            .map { student -> studentMapper.mapToEntity(student) }
            .also { studentEntities -> cache.insertStudents(studentEntities) }

    suspend fun updateAllStudent() = cache.deleteAllStudent().also { insertAllStudentRemote() }

    suspend fun insertAllStudentRemote() =
        remote.getAllStudent().apply { insertAllStudent(students) }

    suspend fun insertAllStudent(studentList: List<Student>) =
        cache.insertStudents(studentList.map { student -> studentMapper.mapToEntity(student) })
}