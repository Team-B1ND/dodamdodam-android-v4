package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import kr.hs.dgsw.smartschool.domain.model.member.Student

data class MyInfoState (
    val isLoading: Boolean = false,
    val myInfo: Student? = null,
    val error: String = ""
)