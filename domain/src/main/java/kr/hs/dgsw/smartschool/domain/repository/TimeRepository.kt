package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.time.Time

interface TimeRepository {
    suspend fun getAllTime(): List<Time>
    suspend fun getStartTime(): String
}