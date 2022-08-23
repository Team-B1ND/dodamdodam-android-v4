package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.request.CreateBusDtoRequest
import kr.hs.dgsw.smartschool.domain.request.ModifyBusDtoRequest

interface BusRepository {

    //GET
    suspend fun getBusList(): List<BusByDate>
    suspend fun getMyBus() : List<Bus>
    suspend fun getMyBusByMonth(
        request : MyBusByMonthRequest
    ) : List<Bus>


    //POST
    suspend fun addBus(
        request: CreateBusDtoRequest
    ):String
    suspend fun addBusApply(
        idx: Int
    ):String


    //PUT
    suspend fun updateBusInfo(
        request : ModifyBusDtoRequest
    ):String
    suspend fun updateBusApply(
        request: ApplyBusDtoRequest
    ):String



    //DELETE
    suspend fun deleteBus(
        idx : Int
    ):String
    suspend fun deleteBusApply(
        idx : Int
    ):String
}
