package kr.hs.dgsw.smartschool.data.database.cache

import android.app.Application
import kr.hs.dgsw.smartschool.data.base.BaseCache
import kr.hs.dgsw.smartschool.data.database.dao.MemberDao
import kr.hs.dgsw.smartschool.data.database.dao.StudentDao
import kr.hs.dgsw.smartschool.data.database.dao.TeacherDao
import kr.hs.dgsw.smartschool.data.database.entity.MemberEntity
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import javax.inject.Inject

class MemberCache @Inject constructor(application: Application) : BaseCache(application) {

    private val memberDao: MemberDao = database.memberDao()
    private val studentDao: StudentDao = database.studentDao()
    private val teacherDao: TeacherDao = database.teacherDao()

    suspend fun insertMember(memberEntity: MemberEntity) = memberDao.insert(memberEntity)

    suspend fun insertMember(memberEntities: List<MemberEntity>) = memberDao.insert(memberEntities)

    suspend fun insertStudents(studentEntities: List<StudentEntity>) = studentDao.insert(studentEntities)

    suspend fun insertTeachers(teacherEntities: List<TeacherEntity>) = teacherDao.insert(teacherEntities)

    suspend fun deleteAllMember() {
        memberDao.deleteAll()
        studentDao.deleteAll()
        teacherDao.deleteAll()
    }

    suspend fun deleteAllTeacher() = teacherDao.deleteAll()

    suspend fun deleteAllStudent() = studentDao.deleteAll()

    suspend fun getMember(id: String): MemberEntity = memberDao.getMember(id)

    suspend fun getStudent(id: String): StudentEntity = studentDao.getStudent(id)

    suspend fun getAllMember(): List<MemberEntity> = memberDao.getAllMember()

    suspend fun getAllStudent(): List<StudentEntity> = studentDao.getAllStudent()

    suspend fun getAllTeacher(): List<TeacherEntity> = teacherDao.getAllTeacher()
}
