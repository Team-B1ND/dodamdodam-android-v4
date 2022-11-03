package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

interface TimeRepository {
    suspend fun getAllTime(): List<TimeTable>
    suspend fun getStartTime(): String
}
