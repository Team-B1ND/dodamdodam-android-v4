package kr.hs.dgsw.smartschool.data.database.cache

import kr.hs.dgsw.smartschool.data.database.dao.BusDao
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import javax.inject.Inject

class BusCache @Inject constructor(
    private val busDao: BusDao
){
    fun insertBusList(date: String, busList: List<BusByDate>){
        if(getBusList(
    }
    fun getBusList(date:String) : List<BusByDate>{

    }
    fun
}