package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.bus.BusByDateResponse
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

fun BusByDateResponse.toModel(): BusByDate = BusByDate(
    busList= this.busList.map { it.toModel() },
    date=this.date
)
