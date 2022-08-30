package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.ClassInfoDataSource
import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.TeacherDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeTableDataSource
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import javax.inject.Inject

class DataSetUpRepositoryImpl @Inject constructor(
    private val teacherDataSource: TeacherDataSource,
    private val classInfoDataSource: ClassInfoDataSource,
    private val placeDataSource: PlaceDataSource,
    private val timeTableDataSource: TimeTableDataSource
) : DataSetUpRepository {

    override suspend fun setUpData(): String {
        classInfoDataSource.updateClassInfoList()
        placeDataSource.updatePlaceList()
        timeTableDataSource.updateTimeList()
        teacherDataSource.updateAllTeacher()

        return "데이터를 업데이트 하였습니다."
    }

    override suspend fun setUpTeacher() {
        teacherDataSource.updateAllTeacher()
    }
}
