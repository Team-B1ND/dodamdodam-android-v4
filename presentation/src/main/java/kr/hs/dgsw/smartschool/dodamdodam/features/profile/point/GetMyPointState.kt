package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint

data class GetMyPointState(
    val isLoading: Boolean = false,
    val myYearPoint: MyYearPoint? = null,
    val error: String = ""
)
