package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.ClassInfoDataSource
import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.TeacherDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeDataSource
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import javax.inject.Inject

class DataSetUpRepositoryImpl @Inject constructor(
    private val teacherDataSource: TeacherDataSource,
    private val classInfoDataSource: ClassInfoDataSource,
    private val placeDataSource: PlaceDataSource,
    private val timeDataSource: TimeDataSource
) : DataSetUpRepository {

    override suspend fun setUpData() {
        classInfoDataSource.updateClassInfoList()
        placeDataSource.updatePlaceList()
        timeDataSource.updateTimeList()
        teacherDataSource.updateAllTeacher()
    }

    override suspend fun setUpTeacher() {
        teacherDataSource.updateAllTeacher()
    }
}