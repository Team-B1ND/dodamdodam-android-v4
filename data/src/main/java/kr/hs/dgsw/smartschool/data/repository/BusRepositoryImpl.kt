package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(

): BusRepository {
    override suspend fun getBusList() {
        TODO("Not yet implemented")
    }

    override suspend fun getTodayBusList() {
        TODO("Not yet implemented")
    }

    override suspend fun getBusSelf() {
        TODO("Not yet implemented")
    }

    override suspend fun getBusSelfApply() {
        TODO("Not yet implemented")
    }
}