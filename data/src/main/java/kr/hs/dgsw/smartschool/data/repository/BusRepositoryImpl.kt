package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.mapper.toRequest
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusParam
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusApplyParam
import kr.hs.dgsw.smartschool.domain.param.bus.UpdateBusParam
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
        request: MyBusByMonthParam
    ): List<Bus> {
        return dataSource.getMyBusByMonth(request).map { it.toModel() }
    }

    override suspend fun addBus(
        request: AddBusParam
    ): String =
        dataSource.addBus(request.toRequest())

    override suspend fun addBusApply(
        idx: Int
    ): String =
        dataSource.addBusApply(idx)

    override suspend fun updateBus(
        id: Int,
        request: UpdateBusParam
    ): String =
        dataSource.updateBus(id, request.toRequest())

    override suspend fun updateBusApply(
        request: UpdateBusApplyParam
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
