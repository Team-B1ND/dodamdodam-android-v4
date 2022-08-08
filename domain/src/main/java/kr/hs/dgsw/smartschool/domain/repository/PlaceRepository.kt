package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.place.Place

interface PlaceRepository {
    suspend fun getAllPlace(): List<Place>
}