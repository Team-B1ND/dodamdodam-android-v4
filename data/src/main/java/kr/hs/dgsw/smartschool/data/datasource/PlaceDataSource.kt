package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.PlaceCache
import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.network.remote.PlaceRemote
import kr.hs.dgsw.smartschool.domain.model.place.Place
import javax.inject.Inject

class PlaceDataSource @Inject constructor(
    override val remote: PlaceRemote,
    override val cache: PlaceCache
) : BaseDataSource<PlaceRemote, PlaceCache> {

    suspend fun updatePlaceList() = cache.deleteAll().also { insertAllClassInfoRemote() }

    suspend fun getAllPlace(): List<PlaceEntity> =
        cache.getAllPlace().let {
            it.ifEmpty { getPlaceRemote() }
        }

    private suspend fun insertAllClassInfoRemote() =
        remote.getAllPlace()
            .also { insertPlaceList(it) }

    private suspend fun getPlaceRemote(): List<PlaceEntity> =
        remote.getAllPlace()
            .map { place -> place.toEntity() }
            .also { placeEntityList -> cache.insertPlace(placeEntityList) }

    private suspend fun insertPlaceList(placeList: List<Place>) =
        cache.insertPlace(placeList.map { place -> place.toEntity() })
}
