package kr.hs.dgsw.smartschool.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.base.BaseDao
import kr.hs.dgsw.smartschool.data.database.entity.BusEntity
import kr.hs.dgsw.smartschool.domain.model.bus.Bus

@Dao
interface BusDao : BaseDao<BusEntity>{
    @Query("SELECT busList From bus_table WHERE date = :date")
    fun getBusList(date : String):List<Bus>

    @Query("INSERT INTO bus_table(date,busList) VALUES(:date,:busList)")
    fun insertBusList(date:String, busList: List<Bus>)

    @Query("UPDATE bus_table SET busList = :busList WHERE date = :date ")
    fun updateBusList(date:String, busList:List<Bus>)
}