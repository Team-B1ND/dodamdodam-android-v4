package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.domain.model.time.Time

class TimeMapper : BaseEntityMapper<Time, TimeEntity> {
    override fun mapToModel(entity: TimeEntity): Time {
        return Time(
            entity.idx,
            entity.name,
            entity.type,
            entity.startTime,
            entity.endTime
        )
    }

    override fun mapToEntity(model: Time): TimeEntity {
        return TimeEntity(
            model.idx,
            model.name,
            model.type,
            model.startTime,
            model.endTime
        )
    }
}
