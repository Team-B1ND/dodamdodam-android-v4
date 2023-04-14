package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.out.OutItem

interface OutRepository {

    suspend fun getAllOut(): List<OutItem>

    suspend fun getOutSleepingById(outSleepingId: Int): OutItem

    suspend fun getMyOutSleeping(): List<OutItem>

    suspend fun applyOutSleeping(startOutDate: String, endOutDate: String, reason: String): OutItem

    suspend fun modifyOutSleeping(startOutDate: String, endOutDate: String, reason: String, outId: Int): OutItem

    suspend fun deleteOutSleeping(outSleepingId: Int): String

    suspend fun getOutGoingById(outGoingId: Int): OutItem

    suspend fun getMyOutGoing(): List<OutItem>

    suspend fun applyOutGoing(startOutDate: String, endOutDate: String, reason: String): OutItem

    suspend fun modifyOutGoing(startOutDate: String, endOutDate: String, reason: String, outId: Int): OutItem

    suspend fun deleteOutGoing(outGoingId: Int): String
}
