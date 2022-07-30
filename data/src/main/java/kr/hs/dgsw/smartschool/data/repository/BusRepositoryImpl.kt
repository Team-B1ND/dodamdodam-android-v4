package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    val dataSource: BusDataSource
): BusRepository {

    override suspend fun getBusList() : List<BusByDate> {
        return dataSource.getBusList().busList
    }

    override suspend fun getMyBus(): List<Bus> {
        return dataSource.getMyBusList().busList
    }

    override suspend fun getMyBusByMonth(
        request: MyBusByMonthRequest
    ): List<Bus> {
        return dataSource.getMyBusByMonth(request).busList
    }

    override suspend fun addBus(request: AddBusRequest)
    = dataSource.addBus(request)

    override suspend fun addBusApply()
    = dataSource.addBusApply()

    override suspend fun updateBus(
        request: UpdateBusRequest
    )
    = dataSource.updateBus(request)

    override suspend fun updateBusApply(
        request: UpdateBusApplyRequest
    )
    =dataSource.updateBusApply(request)

    override suspend fun deleteBus(
        idx : Int
    )
    = dataSource.deleteBus(idx)

    override suspend fun deleteBusApply(
        idx : Int
    )
    = dataSource.deleteBusApply(idx)
}