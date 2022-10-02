package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint

data class MyBonusPointState(
    val isLoading: Boolean = false,
    val bonusPoint: MyYearPoint? = null,
    val error: String = ""
)
