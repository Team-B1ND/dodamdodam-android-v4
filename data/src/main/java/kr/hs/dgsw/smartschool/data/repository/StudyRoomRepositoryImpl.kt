package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.StudyRoomDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeTableDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.mapper.toRequest
import kr.hs.dgsw.smartschool.data.network.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomByTypeParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.DefaultStudyRoomParam
import kr.hs.dgsw.smartschool.domain.param.studyroom.StudyRoomParam
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    private val studyRoomDataSource: StudyRoomDataSource,
    private val timeTableDataSource: TimeTableDataSource,
    private val placeDataSource: PlaceDataSource
) : StudyRoomRepository {

    private lateinit var studyRoomList: List<StudyRoom?>
    private lateinit var defaultStudyRoomList: List<DefaultStudyRoom>
    private lateinit var timeTableList: List<TimeTable>
    private lateinit var placeList: List<Place>

    override suspend fun applyStudyRoom(request: StudyRoomParam): String {
        return studyRoomDataSource.applyStudyRoom(request.toRequest())
    }

    override suspend fun getMyStudyRoom(): List<StudyRoom> {
        this.studyRoomList = studyRoomDataSource.getMyStudyRoom().map { studyRoomResponse ->
            studyRoomResponse!!.toModel()
        }
        this.timeTableList = timeTableDataSource.getAllTime().map { timeEntity ->
            Log.d("TestTest", "getMyStudyRoom: $timeEntity")
            timeEntity.toModel()
        }
        if (studyRoomList.isEmpty()) {
            val initStudyRoomList = mutableListOf<StudyRoom>()
            this.timeTableList.forEach {
                initStudyRoomList.add(StudyRoomResponse(it.toModel()).toModel())
            }
            return initStudyRoomList
        }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeEntity.toModel() }
        return getStudyRoomList()
    }

    private fun getStudyRoomList(): List<StudyRoom> {
        val result: ArrayList<StudyRoom> = ArrayList()

        studyRoomList.forEach { studyRoom ->
            placeList.forEach { place ->
                if (studyRoom?.place?.id == place.id)
                    studyRoom.place = place
            }
        }

        timeTableList.forEachIndexed { index, timeTable ->
            result.add(StudyRoomResponse(timeTable.toModel()).toModel())

            studyRoomList.forEach { studyRoom ->
                if (studyRoom?.timeTable?.id == timeTable.id) {
                    result[index] = StudyRoom(timeTable, studyRoom)
                }
            }
        }
        return result
    }

    override suspend fun modifyAppliedStudyRoom(request: StudyRoomParam): String {
        return studyRoomDataSource.modifyAppliedStudyRoom(request.toRequest())
    }

    override suspend fun getStudyRoomById(id: Int): StudyRoom {
        return studyRoomDataSource.getStudyRoomById(id).toModel()
    }

    override suspend fun cancelStudyRoom(id: Int): String {
        return studyRoomDataSource.cancelStudyRoom(id)
    }

    override suspend fun getDefaultStudyRoom(): List<DefaultStudyRoom> {
        this.defaultStudyRoomList = studyRoomDataSource.getDefaultStudyRoom()
        this.timeTableList = timeTableDataSource.getAllTime().map { timeEntity -> timeEntity.toModel() }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeEntity.toModel() }
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

    override suspend fun createDefaultStudyRoom(request: DefaultStudyRoomParam): String {
        return studyRoomDataSource.createDefaultStudyRoom(request.toRequest())
    }

    override suspend fun createDefaultStudyRoomByWeekType(request: DefaultStudyRoomByTypeParam): String {
        return studyRoomDataSource.createDefaultStudyRoomByWeekType(request.toRequest())
    }
}
