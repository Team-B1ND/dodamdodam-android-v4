package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity

@Dao
interface ClassroomDao : BaseDao<ClassroomEntity> {
    @Query("SELECT * FROM classroom_table WHERE id=(SELECT id FROM student_table WHERE id=:studentIdx)")
    suspend fun getClassInfoByStudentIdx(studentIdx: Int): ClassroomEntity

    @Query("SELECT * FROM classroom_table WHERE id=(SELECT id FROM student_table WHERE id=:memberId)")
    suspend fun getClassInfoByMemberId(memberId: String): ClassroomEntity

    @Query("SELECT * FROM classroom_table WHERE id=:idx")
    suspend fun getClassInfo(idx: Int): ClassroomEntity

    @Query("SELECT * FROM classroom_table")
    suspend fun getAllClassInfo(): List<ClassroomEntity>

    @Query("DELETE FROM classroom_table")
    suspend fun deleteAll()
}
