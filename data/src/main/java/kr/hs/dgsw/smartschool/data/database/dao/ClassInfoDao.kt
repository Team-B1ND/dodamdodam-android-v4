package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.ClassInfoEntity

@Dao
interface ClassInfoDao : BaseDao<ClassInfoEntity> {
    @Query("SELECT * FROM class_info_table WHERE idx=(SELECT classroomIdx FROM student_table WHERE idx=:studentIdx)")
    suspend fun getClassInfoByStudentIdx(studentIdx: Int): ClassInfoEntity

    @Query("SELECT * FROM class_info_table WHERE idx=(SELECT classroomIdx FROM student_table WHERE id=:memberId)")
    suspend fun getClassInfoByMemberId(memberId: String): ClassInfoEntity

    @Query("SELECT * FROM class_info_table WHERE idx=:idx")
    suspend fun getClassInfo(idx: Int): ClassInfoEntity

    @Query("SELECT * FROM class_info_table")
    suspend fun getAllClassInfo(): List<ClassInfoEntity>

    @Query("DELETE FROM class_info_table")
    suspend fun deleteAll()
}