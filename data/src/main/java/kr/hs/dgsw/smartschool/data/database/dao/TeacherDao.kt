package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity

@Dao
interface TeacherDao : BaseDao<TeacherEntity> {

    @Query("SELECT * FROM teacher_table WHERE idx=:idx")
    suspend fun getTeacher(idx: Int): TeacherEntity

    @Query("SELECT * FROM teacher_table")
    suspend fun getAllTeacher(): List<TeacherEntity>

    @Query("DELETE FROM teacher_table")
    suspend fun deleteAll()
}