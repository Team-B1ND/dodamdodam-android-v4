package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.state

import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem

data class NightStudyState(
    val isUpdate: Boolean = false,
    val nightStudyList: List<NightStudyItem> = emptyList(),
    val message: String = "",
    val error: String = ""
)
