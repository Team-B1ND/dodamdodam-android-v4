package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.LocationApi
import kr.hs.dgsw.smartschool.domain.model.location.DefaultLocation
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.location.Locations
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.LocationControlRequest
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocationRemote(override val api: LocationApi) : BaseRemote<LocationApi>() {

    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    suspend fun postLocation(locationRequest: LocationRequest): String =
        api.postLocation(locationRequest).message

    suspend fun getMyLocation(date: String): List<LocationInfo?> =
        api.getMyLocation(date).data.locations

    suspend fun getLocation(): List<Locations> =
        api.getLocation(format.format(Date())).data

    suspend fun getDefaultLocation(day: Int): List<DefaultLocation> =
        api.getDefaultLocation(day).data.defaultLocations.sortedBy { it.timeTableIdx }

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String =
        api.postDefaultLocation(request).message

    suspend fun putAllLocation(request: LocationRequest): String =
        api.putAllLocation(request).message

    suspend fun putLocation(idx: Int, placeIdx: Location): String =
        api.putLocation(idx, placeIdx).message

    suspend fun deleteLocation(idx: Int): String =
        api.deleteLocation(idx).message

    suspend fun checkLocation(idx: Int): String =
        api.checkLocation(idx).message

    suspend fun unCheckLocation(idx: Int): String =
        api.unCheckLocation(idx).message

    suspend fun postLocationControl(request: LocationControlRequest): String =
        api.postLocationControl(request).message
}
