package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.MemberEntity

@Dao
interface MemberDao : BaseDao<MemberEntity> {

    @Query("SELECT * FROM member_table WHERE id=(SELECT id FROM student_table WHERE idx=:idx)")
    suspend fun getMemberByStudentIdx(idx: Int): MemberEntity

    @Query("SELECT * FROM member_table WHERE id=(SELECT id FROM teacher_table WHERE idx=:idx)")
    suspend fun getMemberByTeacherIdx(idx: Int): MemberEntity

    @Query("SELECT * FROM member_table WHERE id=:id")
    suspend fun getMember(id: String): MemberEntity

    @Query("SELECT * FROM member_table")
    suspend fun getAllMember(): List<MemberEntity>

    @Query("DELETE FROM member_table")
    suspend fun deleteAll()
}
