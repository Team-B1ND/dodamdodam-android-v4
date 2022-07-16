package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyPoint

data class GetMyPointState(
    val isLoading: Boolean = false,
    val myPoint: MyPoint? = null,
    val error: String = ""
)
