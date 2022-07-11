package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList
import retrofit2.Response

interface BusRepository {

    //GET
    suspend fun getBusList(): Response<List<Bus>>
    suspend fun getMyBus() : Response<List<Bus>>
    suspend fun getMyBusMonth() : Response<List<Bus>>


    //POST
    suspend fun addBus():Response<Any>
    suspend fun addBusApply():Response<Any>


    //PUT
    suspend fun alterBusInfo():Response<Any>
    suspend fun alterBusApply():Response<Any>



    //DELETE
    suspend fun deleteBus(): Response<Any>
    suspend fun deleteBusApply(): Response<Any>
}