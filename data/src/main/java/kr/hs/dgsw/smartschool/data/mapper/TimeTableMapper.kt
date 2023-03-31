package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.data.network.response.time.TimeTableResponse
import kr.hs.dgsw.smartschool.data.network.response.time.WeekTypeResponse
import kr.hs.dgsw.smartschool.domain.model.time.TimeTable
import kr.hs.dgsw.smartschool.domain.model.time.WeekType

fun TimeEntity.toModel(): TimeTable = TimeTable(
    id = this.id,
    name = this.name,
    type = WeekType.valueOf(this.type),
    startTime = this.startTime,
    endTime = this.endTime
)

fun TimeTable.toEntity(): TimeEntity = TimeEntity(
    id = this.id,
    name = this.name,
    type = this.type.name,
    startTime = this.startTime,
    endTime = this.endTime
)

fun WeekType.toModel(): WeekTypeResponse = when (this) {
    WeekType.WEEKDAY -> WeekTypeResponse.WEEKDAY
    WeekType.WEEKEND -> WeekTypeResponse.WEEKEND
}

fun WeekTypeResponse.toModel(): WeekType = when (this) {
    WeekTypeResponse.WEEKDAY -> WeekType.WEEKDAY
    WeekTypeResponse.WEEKEND -> WeekType.WEEKEND
}

fun TimeTable.toModel(): TimeTableResponse = TimeTableResponse(
    id = this.id,
    name = this.name,
    type = this.type.toModel(),
    startTime = this.startTime,
    endTime = this.endTime
)

fun TimeTableResponse.toModel(): TimeTable = TimeTable(
    id = this.id,
    name = this.name,
    type = this.type.toModel(),
    startTime = this.startTime,
    endTime = this.endTime
)
