package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.write.state

import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem

data class ApplyEveningStudyState(
    val eveningStudyItem: EveningStudyItem? = null,
    val message: String = "",
    val error: String = ""
)
