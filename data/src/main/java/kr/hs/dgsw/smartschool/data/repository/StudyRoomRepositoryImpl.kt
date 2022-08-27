package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.LocationDataSource
import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeDataSource
import kr.hs.dgsw.smartschool.data.mapper.PlaceMapper
import kr.hs.dgsw.smartschool.data.mapper.TimeMapper
import kr.hs.dgsw.smartschool.domain.model.studyroom.DefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.StudyRoomRequest
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val timeDataSource: TimeDataSource,
    private val placeDataSource: PlaceDataSource
) : LocationRepository {

    private val timeMapper = TimeMapper()
    private val placeMapper = PlaceMapper()

    private lateinit var locationInfoList: List<StudyRoom>
    private lateinit var defaultLocationList: List<DefaultStudyRoom>
    private lateinit var timeTableList: List<TimeTable>
    private lateinit var placeList: List<Place>

    override suspend fun postLocation(studyRoomRequest: StudyRoomRequest): String {
        return locationDataSource.postLocation(studyRoomRequest)
    }

    override suspend fun getMyLocation(date: String): List<StudyRoom> {
        this.locationInfoList = locationDataSource.getMyLocation(date).filterNotNull()
        this.timeTableList = timeDataSource.getAllTime().map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getLocationInfoList()
    }

    private fun getLocationInfoList(): List<StudyRoom> {
        val result: ArrayList<StudyRoom> = ArrayList()

        locationInfoList.forEach { locationInfo ->
            placeList.forEach { place ->
                if (locationInfo.placeIdx == place.id)
                    locationInfo.place = place
            }
        }

        timeTableList.forEachIndexed { index, time ->
            result.add(StudyRoom(time))

            locationInfoList.forEach { locationInfo ->
                if (locationInfo.timeTableIdx == time.id) {
                    result[index] = StudyRoom(time, locationInfo)
                }
            }
        }

        return result
    }

    override suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultStudyRoom> {
        this.defaultLocationList = locationDataSource.getDefaultLocation(dayOfWeek)
        this.timeTableList = timeDataSource.getAllTime(dayOfWeek).map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getDefaultLocationList()
    }

    private fun getDefaultLocationList(): List<DefaultStudyRoom> {
        val result: ArrayList<DefaultStudyRoom> = ArrayList()

        defaultLocationList.forEach { defaultLocation ->
            placeList.forEach { place ->
                if (defaultLocation.placeIdx == place.id)
                    defaultLocation.place = place
            }
        }

        timeTableList.forEachIndexed { index, time ->
            result.add(DefaultStudyRoom(time))

            defaultLocationList.forEach { defaultLocation ->
                if (defaultLocation.timeTableIdx == time.id) {
                    result[index] = DefaultStudyRoom(time, defaultLocation)
                }
            }
        }

        return result
    }

    override suspend fun postDefaultLocation(request: DefaultLocationRequest): String {
        return locationDataSource.postDefaultLocation(request)
    }

    override suspend fun putAllLocation(request: StudyRoomRequest): String {
        return locationDataSource.putAllLocation(request)
    }

    override suspend fun putLocation(idx: Int, placeIdx: Location): String {
        return locationDataSource.putLocation(idx, placeIdx)
    }

    override suspend fun deleteLocation(idx: Int): String {
        return locationDataSource.deleteLocation(idx)
    }
}
