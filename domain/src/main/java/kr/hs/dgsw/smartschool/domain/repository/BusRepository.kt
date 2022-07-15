package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

interface BusRepository {

    //GET
    suspend fun getBusList(): List<BusByDate>
    suspend fun getMyBus() : List<Bus>
    suspend fun getMyBusByMonth() : List<Bus>


    //POST
    suspend fun addBus():String
    suspend fun addBusApply():String


    //PUT
    suspend fun updateBusInfo():String
    suspend fun updateBusApply():String



    //DELETE
    suspend fun deleteBus():String
    suspend fun deleteBusApply():String
}