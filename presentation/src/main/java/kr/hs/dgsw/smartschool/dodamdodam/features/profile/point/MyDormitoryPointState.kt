package kr.hs.dgsw.smartschool.dodamdodam.features.profile.point

import kr.hs.dgsw.smartschool.domain.model.point.MyTargetPoint

data class MyDormitoryPointState(
    val isLoading: Boolean = false,
    val myDormitoryPoint: MyTargetPoint? = null,
    val error: String = ""
)
