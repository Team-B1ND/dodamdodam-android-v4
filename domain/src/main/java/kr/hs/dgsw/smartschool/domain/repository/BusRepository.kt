package kr.hs.dgsw.smartschool.domain.repository

interface BusRepository {
    suspend fun getBusList()
    suspend fun getTodayBusList()
    suspend fun getBusSelf()
    suspend fun getBusSelfMonth()
}