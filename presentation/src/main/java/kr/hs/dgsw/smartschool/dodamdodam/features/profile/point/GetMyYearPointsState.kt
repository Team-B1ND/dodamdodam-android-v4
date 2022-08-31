package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint

data class GetMyYearPointsState(
    val isReach: Boolean = false,
    val yearPointList: List<MyYearPoint> = emptyList(),
    val error: String = ""
)
