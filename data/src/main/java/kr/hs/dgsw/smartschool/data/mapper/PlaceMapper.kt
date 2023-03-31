package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity
import kr.hs.dgsw.smartschool.data.network.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.place.PlaceType

fun PlaceEntity.toModel(): Place = Place(
    id = this.id,
    name = this.name,
    type = PlaceType(
        this.typeId,
        this.typeName
    )
)

fun PlaceResponse.toEntity(): PlaceEntity = PlaceEntity(
    id = this.id,
    name = this.name,
    typeId = this.type.id,
    typeName = this.type.name
)
