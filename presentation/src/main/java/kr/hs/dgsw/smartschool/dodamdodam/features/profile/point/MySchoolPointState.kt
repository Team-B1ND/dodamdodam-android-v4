package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyTargetPoint

data class MySchoolPointState(
    val isLoading: Boolean = false,
    val mySchoolPoint: MyTargetPoint? = null,
    val error: String = ""
)
