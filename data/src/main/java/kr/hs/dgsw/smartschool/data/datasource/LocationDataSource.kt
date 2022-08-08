package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.LocationRemote
import kr.hs.dgsw.smartschool.domain.model.location.DefaultLocation
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.location.Locations
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.LocationControlRequest
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    override val remote: LocationRemote,
    override val cache: Any
) : BaseDataSource<LocationRemote, Any>() {

    suspend fun postLocation(locationRequest: LocationRequest): String =
        remote.postLocation(locationRequest)

    suspend fun getMyLocation(date: String): List<LocationInfo?> = remote.getMyLocation(date)

    suspend fun getLocation(): List<Locations> = remote.getLocation()

    suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultLocation> = remote.getDefaultLocation(dayOfWeek)

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String = remote.postDefaultLocation(request)

    suspend fun putAllLocation(request: LocationRequest): String = remote.putAllLocation(request)

    suspend fun putLocation(idx: Int, placeIdx: Location): String = remote.putLocation(idx, placeIdx)

    suspend fun deleteLocation(idx: Int): String = remote.deleteLocation(idx)

    suspend fun checkLocation(idx: Int): String = remote.checkLocation(idx)

    suspend fun unCheckLocation(idx: Int): String = remote.unCheckLocation(idx)

    suspend fun postLocationControl(student: Student, locations: List<LocationInfo>): String =
        remote.postLocationControl(LocationControlRequest(student, locations))
}