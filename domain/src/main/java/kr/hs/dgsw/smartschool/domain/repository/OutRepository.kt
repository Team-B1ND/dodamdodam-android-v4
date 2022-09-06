package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import java.util.*

interface OutRepository {

    suspend fun getOut(year: Int, month: Int, status: OutStatus): Out

    suspend fun getOutByDate(date: Date): Out

    suspend fun getOutSleepingById(outSleepingId: Int): OutItem

    suspend fun getMyOutSleeping(): List<OutItem>

    suspend fun applyOutSleeping(request: OutRequest): OutItem

    suspend fun modifyOutSleeping(request: ModifyOutRequest): OutItem

    suspend fun deleteOutSleeping(outSleepingId: Int): String

    suspend fun getOutGoingById(outGoingId: Int): OutItem

    suspend fun getMyOutGoing(): List<OutItem>

    suspend fun applyOutGoing(request: OutRequest): OutItem

    suspend fun modifyOutGoing(request: ModifyOutRequest): OutItem

    suspend fun deleteOutGoing(outGoingId: Int): String

}
