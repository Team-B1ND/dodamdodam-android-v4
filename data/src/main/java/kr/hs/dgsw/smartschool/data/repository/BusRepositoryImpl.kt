package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.BusDataSource
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    val dataSource: BusDataSource
): BusRepository {
    override suspend fun getBusList() : List<BusList> {
        return dataSource.getBusList()
    }

    override suspend fun getBusSelf() : List<Bus> {
        return dataSource.getBusSelf()
    }

    override suspend fun getBusSelfMonth(): List<Bus> {
        return dataSource.getBusSelfMonth()
    }
}