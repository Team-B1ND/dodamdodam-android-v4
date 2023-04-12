package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MemberCache
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import kr.hs.dgsw.smartschool.data.network.request.member.ModifyMemberInfoRequest
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import javax.inject.Inject

class StudentDataSource @Inject constructor(
    override val remote: MemberRemote,
    override val cache: MemberCache
) : BaseDataSource<MemberRemote, MemberCache> {

    suspend fun getMyInfo(): StudentResponse =
        remote.getMyInfo()

    suspend fun modifyMemberInfo(request: ModifyMemberInfoRequest): String =
        remote.modifyMemberInfo(request)

    suspend fun getStudent(id: String): StudentEntity = cache.getStudent(id)

    suspend fun getAllStudent(): List<StudentEntity> =
        cache.getAllStudent().ifEmpty { getAllStudentRemote() }

    suspend fun getAllStudentRemote(): List<StudentEntity> =
        remote.getStudents()
            .map { studentResponse -> studentResponse.toModel().toEntity() }
            .also { studentEntities -> cache.insertStudents(studentEntities) }

    suspend fun updateAllStudent() = cache.deleteAllStudent().also { insertAllStudentRemote() }

    suspend fun insertAllStudentRemote() =
        insertAllStudent(remote.getStudents())

    suspend fun insertAllStudent(studentList: List<StudentResponse>) =
        cache.insertStudents(studentList.map { studentResponse -> studentResponse.toModel().toEntity() })
}
