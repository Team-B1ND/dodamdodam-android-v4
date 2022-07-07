package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusList

interface BusRepository {
    suspend fun getBusList(): List<BusList>
    suspend fun getBusSelf() : List<Bus>
    suspend fun getBusSelfMonth() : List<Bus>
}