package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import kr.hs.dgsw.smartschool.domain.model.bus.Bus

fun BusResponse.toModel(): Bus = Bus(
    id = this.id,
    busName = this.busName,
    description = this.description,
    peopleLimit = this.peopleLimit,
    leaveTime = this.leaveTime,
    timeRequired = this.timeRequired,
    peopleCount = this.peopleCount
)
