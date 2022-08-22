package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.LocationDataSource
import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.datasource.TimeDataSource
import kr.hs.dgsw.smartschool.data.mapper.PlaceMapper
import kr.hs.dgsw.smartschool.data.mapper.TimeMapper
import kr.hs.dgsw.smartschool.domain.model.location.DefaultLocation
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.time.Time
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val timeDataSource: TimeDataSource,
    private val placeDataSource: PlaceDataSource
) : LocationRepository {

    private val timeMapper = TimeMapper()
    private val placeMapper = PlaceMapper()

    private lateinit var locationInfoList: List<LocationInfo>
    private lateinit var defaultLocationList: List<DefaultLocation>
    private lateinit var timeList: List<Time>
    private lateinit var placeList: List<Place>

    override suspend fun postLocation(locationRequest: LocationRequest): String {
        return locationDataSource.postLocation(locationRequest)
    }

    override suspend fun getMyLocation(date: String): List<LocationInfo> {
        this.locationInfoList = locationDataSource.getMyLocation(date).filterNotNull()
        this.timeList = timeDataSource.getAllTime().map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getLocationInfoList()
    }

    private fun getLocationInfoList(): List<LocationInfo> {
        val result: ArrayList<LocationInfo> = ArrayList()

        locationInfoList.forEach { locationInfo ->
            placeList.forEach { place ->
                if (locationInfo.placeIdx == place.idx)
                    locationInfo.place = place
            }
        }

        timeList.forEachIndexed { index, time ->
            result.add(LocationInfo(time))

            locationInfoList.forEach { locationInfo ->
                if (locationInfo.timeTableIdx == time.idx) {
                    result[index] = LocationInfo(time, locationInfo)
                }
            }
        }

        return result
    }

    override suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultLocation> {
        this.defaultLocationList = locationDataSource.getDefaultLocation(dayOfWeek)
        this.timeList = timeDataSource.getAllTime(dayOfWeek).map { timeEntity -> timeMapper.mapToModel(timeEntity) }
        this.placeList = placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
        return getDefaultLocationList()
    }

    private fun getDefaultLocationList(): List<DefaultLocation> {
        val result: ArrayList<DefaultLocation> = ArrayList()

        defaultLocationList.forEach { defaultLocation ->
            placeList.forEach { place ->
                if (defaultLocation.placeIdx == place.idx)
                    defaultLocation.place = place
            }
        }

        timeList.forEachIndexed { index, time ->
            result.add(DefaultLocation(time))

            defaultLocationList.forEach { defaultLocation ->
                if (defaultLocation.timeTableIdx == time.idx) {
                    result[index] = DefaultLocation(time, defaultLocation)
                }
            }
        }

        return result
    }

    override suspend fun postDefaultLocation(request: DefaultLocationRequest): String {
        return locationDataSource.postDefaultLocation(request)
    }

    override suspend fun putAllLocation(request: LocationRequest): String {
        return locationDataSource.putAllLocation(request)
    }

    override suspend fun putLocation(idx: Int, placeIdx: Location): String {
        return locationDataSource.putLocation(idx, placeIdx)
    }

    override suspend fun deleteLocation(idx: Int): String {
        return locationDataSource.deleteLocation(idx)
    }
}
