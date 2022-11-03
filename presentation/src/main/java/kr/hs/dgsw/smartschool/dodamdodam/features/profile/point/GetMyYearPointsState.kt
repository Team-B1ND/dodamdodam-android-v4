package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.Point

data class GetMyYearPointsState(
    val isReach: Boolean = false,
    val yearPointList: List<Point> = emptyList(),
    val error: String = ""
)
