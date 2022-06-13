package kr.hs.dgsw.smartschool.domain.model.bus

import java.util.*

data class BusList<T>(
    val date:Date,
    val bus: T
)
