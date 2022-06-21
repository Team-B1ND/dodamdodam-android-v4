package kr.hs.dgsw.smartschool.domain.model.bus

import java.util.*

data class BusList(
    val date: Date,
    val busList : List<Bus>, // 버스 이름
)
