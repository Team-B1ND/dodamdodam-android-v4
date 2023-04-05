package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.request.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    val dataSource: BusDataSource
) : BusRepository {

    override suspend fun getBusList(): BusByDate {
        return dataSource.getBusList().toModel()
    }

    override suspend fun getMyBus(): Bus {
        Log.e("BusRepository", "getMyBus")
        return dataSource.getMyBus().toModel()
    }

    override suspend fun getMyBusByMonth(
        year: Int,
        month: Int
    ): List<Bus> {
        return dataSource.getMyBusByMonth(year, month).map { it.toModel() }
    }

    override suspend fun addBus(
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): String =
        dataSource.addBus(AddBusRequest(busName, description, leaveTime, timeRequired, peopleLimit))

    override suspend fun addBusApply(
        idx: Int
    ): String =
        dataSource.addBusApply(idx)

    override suspend fun updateBus(
        id: Int,
        busName: String,
        description: String,
        leaveTime: String,
        timeRequired: String,
        peopleLimit: Int
    ): String =
        dataSource.updateBus(
            id,
            busName,
            description,
            leaveTime,
            timeRequired,
            peopleLimit
        )

    override suspend fun updateBusApply(
        busId: Int,
        originBusId: Int
    ): String =
        dataSource.updateBusApply(busId, originBusId)

    override suspend fun deleteBus(
        idx: Int
    ): String =
        dataSource.deleteBus(idx)

    override suspend fun deleteBusApply(
        idx: Int
    ): String {
        Log.e("deleteBusApply", "정상적 실행")
        return dataSource.deleteBusApply(idx)
    }
}
