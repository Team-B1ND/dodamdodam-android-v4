package kr.hs.dgsw.smartschool.domain.repository

interface DataSetUpRepository {
    suspend fun setUpData()
    suspend fun setUpTeacher()
}