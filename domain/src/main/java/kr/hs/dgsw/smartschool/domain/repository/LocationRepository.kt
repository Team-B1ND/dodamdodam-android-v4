package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.location.DefaultLocation
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.request.DefaultLocationRequest
import kr.hs.dgsw.smartschool.domain.request.LocationRequest

interface LocationRepository {

    suspend fun postLocation(locationRequest: LocationRequest): String

    suspend fun getMyLocation(date: String): List<LocationInfo>

    suspend fun getDefaultLocation(dayOfWeek: Int): List<DefaultLocation>

    suspend fun postDefaultLocation(request: DefaultLocationRequest): String

    suspend fun putAllLocation(request: LocationRequest): String

    suspend fun putLocation(idx: Int, placeIdx: Location): String

    suspend fun deleteLocation(idx: Int): String
}
