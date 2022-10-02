package kr.hs.dgsw.smartschool.dodamdodam.features.out.write.state

import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class ApplyOutSleepingState(
    val outItem: OutItem? = null,
    val message: String = "",
    val error: String = ""
)
