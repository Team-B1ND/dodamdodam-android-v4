package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import kr.hs.dgsw.smartschool.domain.request.OutRequest

interface OutRepository {

    suspend fun getOut(date: String): List<OutItem>

    suspend fun getOutAllows(date: String): List<OutItem>

    suspend fun getOutSleepingById(outSleepingIdx: Int): OutSleeping

    suspend fun getOutGoingById(outGoingIdx: Int): OutGoing

    suspend fun postOutSleeping(request: OutRequest): String

    suspend fun putOutSleeping(request: OutRequest): String

    suspend fun deleteOutSleeping(outSleepingIdx: Int): String

    suspend fun postOutGoing(request: OutRequest): String

    suspend fun putOutGoing(request: OutRequest): String

    suspend fun deleteOutGoing(outGoingIdx: Int): String
}
