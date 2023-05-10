package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.write.state

import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem

data class NightStudyWriteState(
    val nightStudyItem: NightStudyItem? = null,
    val message: String = "",
    val error: String = ""
)
