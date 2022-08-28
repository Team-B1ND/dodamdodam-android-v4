package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.StudyRoomDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeTableDataSource
import kr.hs.dgsw.smartschool.data.mapper.PlaceMapper
import kr.hs.dgsw.smartschool.data.mapper.TimeTableMapper
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomByTypeRequest
import kr.hs.dgsw.smartschool.domain.request.DefaultStudyRoomRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    private val studyRoomDataSource: StudyRoomDataSource,
    private val timeTableDataSource: TimeTableDataSource,
    private val placeDataSource: PlaceDataSource
) : StudyRoomRepository {

    private val timeMapper = TimeTableMapper()
    private val placeMapper = PlaceMapper()

    private lateinit var studyRoomList: List<StudyRoom>
    private lateinit var defaultStudyRoomList: List<DefaultStudyRoom>
    private lateinit var timeTableList: List<TimeTable>
    private lateinit var placeList: List<Place>

    override suspend fun applyStudyRoom(request: StudyRoomRequest): String {
        return studyRoomDataSource.applyStudyRoom(request)
    }

    override suspend fun getMyStudyRoom(): List<StudyRoom> {
        this.studyRoomList = studyRoomDataSource.getMyStudyRoom()
        this.timeTableList = timeTableDataSource.getAllTime().map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getStudyRoomList()
    }

    private fun getStudyRoomList(): List<StudyRoom> {
        val result: ArrayList<StudyRoom> = ArrayList()

        studyRoomList.forEach { studyRoom ->
            placeList.forEach { place ->
                if (studyRoom.place?.id == place.id)
                    studyRoom.place = place
            }
        }

        timeTableList.forEachIndexed { index, timeTable ->
            result.add(StudyRoom(timeTable))

            studyRoomList.forEach { studyRoom ->
                if (studyRoom.timeTable.id == timeTable.id) {
                    result[index] = StudyRoom(timeTable, studyRoom)
                }
            }
        }
        return result
    }

    override suspend fun modifyAppliedStudyRoom(request: StudyRoomRequest): String {
        return modifyAppliedStudyRoom(request)
    }

    override suspend fun getStudyRoomById(id: Int): StudyRoom {
        return getStudyRoomById(id)
    }

    override suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> {
        this.defaultStudyRoomList = studyRoomDataSource.getDefaultStudyRoom()
        this.timeTableList = timeTableDataSource.getAllTime().map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getDefaultStudyRoomList()
    }

    private fun getDefaultStudyRoomList(): List<DefaultStudyRoom> {
        val result: ArrayList<DefaultStudyRoom> = ArrayList()

        defaultStudyRoomList.forEach { defaultStudyRoom ->
            placeList.forEach { place ->
                if (defaultStudyRoom.place?.id == place.id)
                    defaultStudyRoom.place = place
            }
        }

        timeTableList.forEachIndexed { index, timeTable ->
            result.add(DefaultStudyRoom(timeTable))

            defaultStudyRoomList.forEach { defaultStudyRoom ->
                if (defaultStudyRoom.timeTable.id == timeTable.id) {
                    result[index] = DefaultStudyRoom(timeTable, defaultStudyRoom)
                }
            }
        }
        return result
    }

    override suspend fun createDefaultStudyRoom(request: DefaultStudyRoomRequest): String {
        return createDefaultStudyRoom(request)
    }

    override suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeRequest): String {
        return createDefaultStudyRoomByWeekType(request)
    }

}
