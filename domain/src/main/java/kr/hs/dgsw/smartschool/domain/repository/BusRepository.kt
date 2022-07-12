package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

interface BusRepository {

    //GET
    suspend fun getBusList(): List<BusByDate>
    suspend fun getMyBus() : List<Bus>
    suspend fun getMyBusByMonth() : List<Bus>


    //POST
    suspend fun addBus()
    suspend fun addBusApply()


    //PUT
    suspend fun updateBusInfo()
    suspend fun updateBusApply()



    //DELETE
    suspend fun deleteBus()
    suspend fun deleteBusApply()
}