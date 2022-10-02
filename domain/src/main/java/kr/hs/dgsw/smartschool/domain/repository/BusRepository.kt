package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest

interface BusRepository {

    // GET
    suspend fun getBusList(): List<BusByDate>
    suspend fun getMyBus(): List<Bus>
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
    suspend fun updateBusInfo(
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
