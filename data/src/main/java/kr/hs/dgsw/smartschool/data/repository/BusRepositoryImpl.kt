package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
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

    override suspend fun addBus():String {
        
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