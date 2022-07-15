package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    val dataSource: BusDataSource
): BusRepository {
    override suspend fun getBusList() : List<BusByDate> {
        return dataSource.getBusList()
    }

    override suspend fun getMyBus(): List<Bus> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyBusByMonth(): List<Bus> {
        TODO("Not yet implemented")
    }

    override suspend fun addBus():String {
        TODO("Not yet implemented")
    }

    override suspend fun addBusApply():String {
        TODO("Not yet implemented")
    }

    override suspend fun updateBusInfo() :String{
        TODO("Not yet implemented")
    }

    override suspend fun updateBusApply() :String{
        TODO("Not yet implemented")
    }

    override suspend fun deleteBus() :String{
        TODO("Not yet implemented")
    }

    override suspend fun deleteBusApply():String{
        TODO("Not yet implemented")
    }
}