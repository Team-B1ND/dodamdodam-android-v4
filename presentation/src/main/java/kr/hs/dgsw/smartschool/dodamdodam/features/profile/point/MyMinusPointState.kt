package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint

data class MyMinusPointState(
    val isLoading: Boolean = false,
    val minusPoint: MyYearPoint? = null,
    val error: String = ""
)
