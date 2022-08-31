package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.model.time.WeekType

class TimeTableMapper : BaseEntityMapper<TimeTable, TimeEntity> {
    override fun mapToModel(entity: TimeEntity): TimeTable {
        return TimeTable(
            entity.id,
            entity.name,
            WeekType.valueOf(entity.type),
            entity.startTime,
            entity.endTime
        )
    }

    override fun mapToEntity(model: TimeTable): TimeEntity {
        return TimeEntity(
            model.id,
            model.name,
            model.type.name,
            model.startTime,
            model.endTime
        )
    }
}
