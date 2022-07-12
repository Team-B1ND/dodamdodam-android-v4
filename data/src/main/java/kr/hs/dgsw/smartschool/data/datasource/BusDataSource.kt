package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.BusCache
import kr.hs.dgsw.smartschool.data.mapper.BusMapper
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import javax.inject.Inject

class BusDataSource @Inject constructor(
    override val cache: BusCache,
    override val remote: BusRemote
)
    :BaseDataSource<BusRemote,Any>() {
        private val mapper = BusMapper()


    suspend fun getBusSelfMonth():List<Bus>{
        return remote.getMyBusListMonth().data
    }
    suspend fun getBusSelf():List<Bus>{
        return remote.getMyBusList().data
    }
    suspend fun getBusList():List<BusByDate>{
        return remote.getBusList().data
    }
}