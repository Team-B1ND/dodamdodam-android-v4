package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.PlaceDataSource
import kr.hs.dgsw.smartschool.data.mapper.PlaceMapper
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource
) : PlaceRepository {

    private val placeMapper = PlaceMapper()

    override suspend fun getAllPlace(): List<Place> {
        return placeDataSource.getAllPlace().map { placeEntity -> placeMapper.mapToModel(placeEntity) }
    }
}
