package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity

@Dao
interface StudentDao : BaseDao<StudentEntity> {

    @Query("SELECT * FROM student_table WHERE id=:id")
    suspend fun getStudent(id: String): StudentEntity

    @Query("SELECT * FROM student_table")
    suspend fun getAllStudent(): List<StudentEntity>

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()
}