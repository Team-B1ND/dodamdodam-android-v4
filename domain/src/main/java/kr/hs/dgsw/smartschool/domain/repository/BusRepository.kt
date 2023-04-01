package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusParam
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusParam

interface BusRepository {

    // GET
    suspend fun getBusList(): BusByDate
    suspend fun getMyBus(): Bus
    suspend fun getMyBusByMonth(
        request: MyBusByMonthParam
    ): List<Bus>

    // POST
    suspend fun addBus(
        request: AddBusParam
    ): String

    suspend fun addBusApply(
        idx: Int
    ): String

    // PUT
    suspend fun updateBus(
        id: Int,
        request: UpdateBusParam
    ): String
    suspend fun updateBusApply(
        request: UpdateBusApplyParam
    ): String

    // DELETE
    suspend fun deleteBus(
        idx: Int
    ): String
    suspend fun deleteBusApply(
        idx: Int
    ): String
}
