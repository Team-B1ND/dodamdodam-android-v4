package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusRequest

interface BusRepository {

    // GET
    suspend fun getBusList(): BusByDate
    suspend fun getMyBus(): Bus
    suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): List<Bus>

    // POST
    suspend fun addBus(
        request: AddBusRequest
    ): String

    suspend fun addBusApply(
        idx: Int
    ): String

    // PUT
    suspend fun updateBus(
        id: Int,
        request: UpdateBusRequest
    ): String
    suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ): String

    // DELETE
    suspend fun deleteBus(
        idx: Int
    ): String
    suspend fun deleteBusApply(
        idx: Int
    ): String
}
