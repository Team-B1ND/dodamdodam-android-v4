package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.state

import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem

data class GetMyEveningStudyState(
    val isUpdate: Boolean = false,
    val eveningStudyList: List<EveningStudyItem> = emptyList(),
    val error: String = ""
)
