package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity
import kr.hs.dgsw.smartschool.domain.model.place.Place

class PlaceMapper : BaseEntityMapper<Place, PlaceEntity> {
    override fun mapToModel(entity: PlaceEntity): Place {
        return Place(
            entity.idx,
            entity.name
        )
    }

    override fun mapToEntity(model: Place): PlaceEntity {
        return PlaceEntity(
            model.id,
            model.name
        )
    }
}
