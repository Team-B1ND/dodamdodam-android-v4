package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.data.network.request.bus.UpdateBusRequest
import kr.hs.dgsw.smartschool.data.network.response.bus.BusResponse
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusParam

fun BusResponse.toModel(): Bus = Bus(
    id = this.id,
    busName = this.busName,
    description = this.description,
    peopleLimit = this.peopleLimit,
    leaveTime = this.leaveTime,
    timeRequired = this.timeRequired,
    peopleCount = this.peopleCount
)

fun UpdateBusParam.toRequest(): UpdateBusRequest = UpdateBusRequest(
    busName = this.busName,
    description = this.description,
    leaveTime = this.leaveTime,
    timeRequired = this.timeRequired,
    peopleLimit = this.peopleLimit
)

fun AddBusParam.toRequest(): AddBusRequest = AddBusRequest(
    busName = this.busName,
    description = this.description,
    leaveTime = this.leaveTime,
    timeRequired = this.timeRequired,
    peopleLimit = this.peopleLimit
)
