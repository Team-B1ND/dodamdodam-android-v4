package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MemberCache
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import kr.hs.dgsw.smartschool.data.mapper.TeacherMapper
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import kr.hs.dgsw.smartschool.domain.model.member.Teacher
import javax.inject.Inject

class TeacherDataSource @Inject constructor(
    override val remote: MemberRemote,
    override val cache: MemberCache
) : BaseDataSource<MemberRemote, MemberCache>() {

    private val teacherMapper = TeacherMapper()

    suspend fun getAllTeacher(): List<TeacherEntity> = cache.getAllTeacher().ifEmpty { getAllTeacherRemote() }

    suspend fun getAllTeacherRemote(): List<TeacherEntity> =
        remote.getAllTeacher().teachers
            .map { teacher -> teacherMapper.mapToEntity(teacher) }
            .also { teacherEntities -> cache.insertTeachers(teacherEntities) }

    suspend fun updateAllTeacher() = cache.deleteAllTeacher().also { insertAllTeacherRemote() }

    suspend fun insertAllTeacherRemote() =
        remote.getAllTeacher().also { insertAllTeacher(it.teachers) }

    suspend fun insertAllTeacher(teacherList: List<Teacher>) =
        cache.insertTeachers(teacherList.map { teacher -> teacherMapper.mapToEntity(teacher) })
}
