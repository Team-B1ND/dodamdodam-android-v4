package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusRequest
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusRequest
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
        request: MyBusByMonthRequest
    ): List<Bus> {
        return dataSource.getMyBusByMonth(request).map { it.toModel() }
    }

    override suspend fun addBus(
        request: AddBusRequest
    ): String =
        dataSource.addBus(request)

    override suspend fun addBusApply(
        idx: Int
    ): String =
        dataSource.addBusApply(idx)

    override suspend fun updateBus(
        id: Int,
        request: UpdateBusRequest
    ): String =
        dataSource.updateBus(id, request)

    override suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    ): String =
        dataSource.updateBusApply(request)

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
