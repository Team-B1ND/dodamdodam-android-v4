package kr.hs.dgsw.smartschool.domain.repository

interface DataSetUpRepository {
    suspend fun setUpData(): String
    suspend fun setUpTeacher()
}
