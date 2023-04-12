package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

interface BusRepository {

    // GET
    suspend fun getBusList(): BusByDate
    suspend fun getMyBus(): Bus?
    suspend fun getMyBusByMonth(
        year: Int,
        month: Int
    ): List<Bus>

    // POST
    suspend fun addBus(
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): String

    suspend fun addBusApply(
        idx: Int
    ): String

    // PUT
    suspend fun updateBus(
        id: Int,
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): String
    suspend fun updateBusApply(
        busId: Int,
        originBusId: Int
    ): String

    // DELETE
    suspend fun deleteBus(
        idx: Int
    ): String
    suspend fun deleteBusApply(
        idx: Int
    ): String
}
