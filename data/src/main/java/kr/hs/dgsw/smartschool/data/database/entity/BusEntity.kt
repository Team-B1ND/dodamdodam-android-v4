package kr.hs.dgsw.smartschool.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.domain.model.bus.Bus

@Entity(tableName = "bus_table")
data class BusEntity(
    @PrimaryKey
    val date : String,
    val busList : List<Bus>
)
